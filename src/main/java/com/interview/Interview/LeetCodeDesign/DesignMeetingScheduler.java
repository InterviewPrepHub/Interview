package com.interview.Interview.LeetCodeDesign;

import java.util.*;

/*
Design Meeting Scheduler. Here there are n given meeting rooms. Book a meeting in any meeting room at given interval
(starting time, end time). Also send notifications to all person who are invited for meeting.
You should use calender for tracking date and time. And also history of all the meetings which are booked and meeting room.
write an API for client who will give date and time and API should return meeting room with booked scheduled time.
client should also query for history of last 20 booked meetings.
Is meeting room available? etc
 */
public class DesignMeetingScheduler {

    //1. create meetings on specific intervals.
    //2. create meeting rooms
    //3. check if same meeting room could be available for two meeting or are they colliding

    //1. book a meeting room from search across n meeting rooms. So create 4 meeting rooms
    //2. given n meeting rooms, say a smaller number like 3 or 4, check if meeting rooms are available
    //3. fetch the last 20 booked meetings
    //4. notify all attendees


    interface MeetingScheduler {

        List<Integer> getAvailableRooms(Interval interval);

        void bookingMeetingRoom(Interval interval, List<Integer> attendees);

        void sendNotifications(Meeting meeting, List<Integer> attendees);

        List<Meeting> showPast20Meetings();
    }

    interface NotificationService {
        void sendNotifications(Meeting meeting, List<Integer> attendees);
    }

    static class EmailNotificationService implements NotificationService {


        @Override
        public void sendNotifications(Meeting meeting, List<Integer> attendees) {

            for (Integer attendee : attendees) {
                System.out.println("Notifying : "+attendee+" about meeting at "+meeting.interval.startTime +" in room : "+ meeting.getRoomId());
            }
        }
    }

    static class MeetingSchedulerImpl implements MeetingScheduler {

        List<MeetingRoom> rooms;
        ArrayDeque<Meeting> meetingHistory = new ArrayDeque<>();
        NotificationService notificationService;

        MeetingSchedulerImpl() {
            rooms = new ArrayList<>();
            notificationService = new EmailNotificationService();
        }

        //create meeting rooms

        public void createMeetingRoom(int roomId) {
            rooms.add(new MeetingRoom(roomId));
        }


        @Override
        public List<Integer> getAvailableRooms(Interval interval) {

            List<Integer> availableRoom = new ArrayList<>();
            for(MeetingRoom room : rooms) {
                if(room.isAvailable(interval)) {
                    availableRoom.add(room.getRoomId());
                }
            }
            return availableRoom;
        }

        @Override
        public void bookingMeetingRoom(Interval interval, List<Integer> attendees) {

            //get list of available rooms
            List<Integer> availableRooms = getAvailableRooms(interval);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms are available");
                return;
            }

            //create a new Meeting
            Meeting meeting = null;
            int roomId = availableRooms.get(0);
            meeting = new Meeting(interval, roomId, attendees);

            for (MeetingRoom room : rooms) {
                if (room.roomId == roomId) {
                    room.addMeeting(meeting);
                    break;
                }
            }


            addToHistory(meeting);
            sendNotifications(meeting, attendees);
        }

        private void addToHistory(Meeting meeting) {
            if (meeting == null) return;

            if (meetingHistory.size() > 20) {
                meetingHistory.removeLast();
            }
            meetingHistory.addFirst(meeting);
        }

        @Override
        public void sendNotifications(Meeting meeting, List<Integer> attendees) {
            notificationService.sendNotifications(meeting, attendees);
        }

        @Override
        public List<Meeting> showPast20Meetings() {
            return meetingHistory.stream().toList();
        }

        public static void main(String[] args) {

            MeetingSchedulerImpl m = new MeetingSchedulerImpl();
            //create meeting room
            m.createMeetingRoom(101);
            m.createMeetingRoom(102);
            m.createMeetingRoom(103);
            m.createMeetingRoom(104);

            //Create dummy attendees
            List<Integer> attendees1 = Arrays.asList(1,2,3,4);
            List<Integer> attendee2 = Arrays.asList(2,3);
            List<Integer> attendee3 = Arrays.asList(3,4);

            //book meeting rooms
            //book a meeting with empty slot from 9 am to 10 am
            m.bookingMeetingRoom(new Interval(9,10), attendees1);

            //book another meeting in the same slot from 9 am to 10 am
            m.bookingMeetingRoom(new Interval(9,10), attendee2);

            //book a new meeting with different slots
            m.bookingMeetingRoom(new Interval(14,15), attendee3);

            List<Meeting> meetings = m.showPast20Meetings();
            for (Meeting meeting : meetings) {
                System.out.println("Meeting from "+meeting.interval.startTime+" to "+meeting.interval.endTime+" in room "+ meeting.getRoomId());
            }

            //check availabilty of room
            Interval newSlot = new Interval(12,13);
            List<Integer> availableRooms = m.getAvailableRooms(newSlot);
            for (Integer availableRoom : availableRooms) {
                System.out.println("rooms available : "+availableRooms);
            }
        }
    }

    static class Interval {
        int startTime;
        int endTime;

        public Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean overlaps(Interval interval) {
            return this.endTime > interval.startTime ? true : false;
        }
    }

    static class Meeting {
        Interval interval;
        List<Integer> attendees;
        int roomId;

        public Meeting(Interval interval, int roomId, List<Integer> attendees) {
            this.interval = interval;
            this.attendees = attendees;
            this.roomId = roomId;
        }

        public Interval getInterval() {
            return interval;
        }

        public List<Integer> getAttendees() {
            return attendees;
        }

        public int getRoomId() {
            return roomId;
        }
    }

    static class MeetingRoom {
        int roomId;
        List<Meeting> scheduledMeetings;

        public MeetingRoom(int roomId) {
            this.roomId = roomId;
            this.scheduledMeetings = new ArrayList<>();
        }

        public void addMeeting(Meeting meeting) {
            scheduledMeetings.add(meeting);
        }

        public List<Meeting> getScheduledMeetings() {
            return Collections.unmodifiableList(scheduledMeetings);
        }

        public boolean isAvailable(Interval interval) {
            for (Meeting meeting: scheduledMeetings) {
                if(meeting.getInterval().overlaps(interval)) {
                    return false; //---> found overlap
                }
            }
            return true;
        }

        public int getRoomId() {
            return roomId;
        }
    }
}

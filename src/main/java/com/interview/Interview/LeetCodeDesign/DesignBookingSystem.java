package com.interview.Interview.LeetCodeDesign;

import java.util.Map;
import java.util.TreeMap;

public class DesignBookingSystem {

    private TreeMap<Integer, Integer> calendar;

    public DesignBookingSystem() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {  //{10,20} {15,25} {20,30}
        // Find the interval that ends just before 'start'
        Integer prev = calendar.floorKey(start); // null, 10, 15  --> returns the greatest key less than or equal to the given key.
        if (prev != null && calendar.get(prev) > start) {
            return false; // Overlaps with previous interval
        }

        // Find the interval that starts just after or at 'start'
        Integer next = calendar.ceilingKey(start);  // returns the least key greater than or equal to the given key.
        if (next != null && end > next) {
            return false; // Overlaps with next interval
        }

        // No overlap, safe to add
        calendar.put(start, end);
        return true;
    }

    // Optional: for debugging / visual inspection
    public void printCalendar() {
        for (Map.Entry<Integer, Integer> entry : calendar.entrySet()) {
            System.out.println("[" + entry.getKey() + ", " + entry.getValue() + ")");
        }
    }

    public static void main(String[] args) {
        DesignBookingSystem booking = new DesignBookingSystem();

        System.out.println(booking.book(10, 20)); // true
        System.out.println(booking.book(15, 25)); // false
        System.out.println(booking.book(20, 30)); // true
        System.out.println(booking.book(5, 10));  // true
        System.out.println(booking.book(0, 5));   // true
        System.out.println(booking.book(17, 19)); // false
        System.out.println(booking.book(30, 40)); // true
        System.out.println(booking.book(35, 45)); // false

        System.out.println("\nFinal calendar:");
        booking.printCalendar();
    }
}

package com.interview.Interview.LeetCodeDesign;

import java.util.*;

public class DesignMusicPlayerApp {


    interface PlayList {

        int addSong(String songTitle); // add a song to your music player with incremental song ids starting from 1
        void playSong(int songId, int userId); // user plays a song that is present in the music player
        void printMostPlayedSongs(); // print song titles in decreasing order of number of unique users' plays


        /*
        songA --> 1,2,3,4,5
        songB --> 1
        songC --> 2,3

        songA, songC, songB

        song class ----> id, title, Set<userId>
         */

    }

    static class PlayListImpl implements PlayList {

        Map<Integer, Song> songLookUp = new HashMap<>();
        int uniqueSongId = 1;

        Map<Integer, ArrayDeque<Song>> userLookUp = new HashMap<>();

        @Override
        public int addSong(String songTitle) {
            songLookUp.put(uniqueSongId, new Song(uniqueSongId, songTitle));
            uniqueSongId++;
            return uniqueSongId;
        }

        @Override
        public void playSong(int songId, int userId) {  //create an event
            Song song = songLookUp.get(songId);

            if(song == null) {
                System.out.println("This song does not exists in the playlist");
                return;
            }
            song.play(userId);

            if (userLookUp.containsKey(userId)) {
                ArrayDeque<Song> songQueue = userLookUp.get(userId);
                //we need to check if this songs song Id exists in this song queue
                //if the song already exists then we dont add it in the song queue
                //else we add it in the song queue
                //however we have incremented the totalPlayCount of this song via method play(userId)
                songQueue.add(song);
            } else {
                ArrayDeque<Song> q = new ArrayDeque<>();
                q.add(song);
                userLookUp.put(userId, q);
            }
        }

        @Override
        public void printMostPlayedSongs() {
            List<Song> songs = new ArrayList<>(songLookUp.values());

            Collections.sort(songs, new Comparator<Song>() {
                @Override
                public int compare(Song a, Song b) {
                    return b.getUniqueUsersCount() - a.getUniqueUsersCount();
                }
            });

            System.out.println("most played song : "+songs.get(0).songTitle);

            /*for (Song song: songs) {
                System.out.println(song.songTitle);
            }*/
        }

        //get last 3 songs played by given user
        public void getLastThreeSongs(int userId) {

           ArrayDeque<Song> songs = userLookUp.get(userId);
           int k = 3;

           while(k != 0) {
               System.out.println("song title: "+songs.removeLast().songTitle);
               k--;
           }
        }

        //get the most trending song in the last 1 hour
        public void getMostTrendingSong() {

            /*List<Song> songs = songLookUp.values().stream().toList();

            Collections.sort(songs, new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return o2.getRecentPlayTimeCount() - o1.getRecentPlayTimeCount();
                }
            });

//            System.out.println(songs.get(0).songTitle +" => "+songs.get(0).getRecentPlayTimeCount());
            for (Song song : songs) {
                System.out.println(song.songTitle + " => "+song.getRecentPlayTimeCount());
            }*/

            List<Song> songs = new ArrayList<>(songLookUp.values());

            Collections.sort(songs, new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return Integer.compare(o2.getRecentPlayTimeCount(), o1.getRecentPlayTimeCount());
                }
            });

            System.out.println(songs.get(0).songTitle +" => "+songs.get(0).getRecentPlayTimeCount());
//            for (Song song : songs) {
//                System.out.println(
//                        song.songTitle + " => " + song.getRecentPlayTimeCount() + " plays (recent)");
//            }

        }

        public static void main(String[] args) {
            PlayListImpl p = new PlayListImpl();
            p.addSong("SongA");
            p.addSong("SongB");
            p.addSong("SongC");
            p.addSong("SongD");


            p.playSong(1,1);
            p.playSong(1,2);
            p.playSong(1,3);
            p.playSong(1,4);
            p.playSong(1,5);
            p.playSong(2,1);
            p.playSong(3,1);
            p.playSong(3,2);
            p.playSong(3,4);
            p.playSong(4,1);
            p.playSong(4,2);
            p.playSong(4,3);
            p.playSong(4,4);

            for(Map.Entry<Integer, Song> entry : p.songLookUp.entrySet()) {
                System.out.println("Song id: "+entry.getKey()+ ", Song: "+entry.getValue().songTitle + ", user : "+entry.getValue().user);
            }

            p.printMostPlayedSongs();

            p.getLastThreeSongs(1);

            p.getMostTrendingSong();
        }
    }


    static class Song {
        int songId;
        String songTitle;
        Set<Integer> user;
        int totalPlayCount;
        ArrayDeque<Long> recentPlayTime;

        Song(int songId, String songTitle) {
            this.songId = songId;
            this.songTitle = songTitle;
            this.user = new HashSet<>();
            this.totalPlayCount = 0;
            this.recentPlayTime = new ArrayDeque<>();
        }

        public void play(int userId) {
            user.add(userId);
            totalPlayCount++;

            long now = System.currentTimeMillis();
            recentPlayTime.addLast(now);
            long oneHourAgo = now - (60 * 60 * 1000); // 1 hour time window

            while (!recentPlayTime.isEmpty() && recentPlayTime.peekFirst() < oneHourAgo) {
                recentPlayTime.removeFirst();
            }
        }

        public int getUniqueUsersCount() {
            return user.size();
        }

        public int getRecentPlayTimeCount() {
            return recentPlayTime.size();
        }
    }
}

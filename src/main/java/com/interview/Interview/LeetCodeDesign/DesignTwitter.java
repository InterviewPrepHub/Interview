package com.interview.Interview.LeetCodeDesign;

import java.util.*;

/*
Implement a simple twitter. Support the following method:

postTweet(user_id, tweet_text). Post a tweet.
getTimeline(user_id). Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
getNewsFeed(user_id). Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
follow(from_user_id, to_user_id). from_user_id followed to_user_id.
unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.

 */
public class DesignTwitter {

/*
    for follow and unfollow - we need a hashmap where a userId can follow many userIds
    so we should be able to add and remove from that list of userIds, so we should use hashset
    HashMap<userId, HashSet<userIds>>

    for posting a tweet by a userId we have a HashMap where a userId can post list of tweets.
    HashMap<userId, List<Tweet>>

*/

    // followerId -> set of followeeIds
    private final Map<Integer, Set<Integer>> followMap = new HashMap<>();
    // userId -> list of tweets (append-only)
    private final Map<Integer, List<Tweet>> tweetMap = new HashMap<>();

    // global, strictly increasing timestamp for deterministic ordering
    private static long TS = 0;

    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweets = tweetMap.get(userId);
        if (tweets == null) {
            tweets = new ArrayList<>();
            tweetMap.put(userId, tweets);
        }
        tweets.add(new Tweet(userId, tweetId, ++TS));
    }

    /*
    Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users
    who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.

     */

    // User's own 10 most recent tweets
    public List<Integer> getTimeline(int userId) {
        List<Integer> res = new ArrayList<>(10);
        List<Tweet> mine = tweetMap.get(userId);
        if (mine == null || mine.isEmpty()) return res;

        // take from the end (most recent appended)
        for (int i = mine.size() - 1; i >= 0 && res.size() < 10; i--) {
            res.add(mine.get(i).tweetId);
        }
        return res;
    }

    // User + followees, 10 most recent by timestamp (max-heap)
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>(10);
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> Long.compare(b.timestamp, a.timestamp));

        // add self tweets
        List<Tweet> mine = tweetMap.get(userId);
        if (mine != null) pq.addAll(mine);

        // add followees' tweets
        Set<Integer> followees = followMap.get(userId);
        if (followees != null) {
            for (int f : followees) {
                List<Tweet> tl = tweetMap.get(f);
                if (tl != null) pq.addAll(tl);
            }
        }

        while (!pq.isEmpty() && res.size() < 10) {
            res.add(pq.poll().tweetId);
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // no-op
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // no-op
        Set<Integer> set = followMap.get(followerId);
        if (set != null) {
            set.remove(followeeId);
            if (set.isEmpty()) followMap.remove(followerId);
        }
    }

    // ----- Demo -----
    public static void main(String[] args) throws InterruptedException {
        DesignTwitter twitter = new DesignTwitter();

        // Users 1 and 2 post tweets
        twitter.postTweet(1, 101);   // ts=1
        twitter.postTweet(1, 102);   // ts=2
        twitter.postTweet(2, 201);   // ts=3
        twitter.postTweet(2, 202);   // ts=4
        twitter.postTweet(2, 203);   // ts=5

        // Timelines (own tweets only)
        System.out.println("Timeline(1): " + twitter.getTimeline(1)); // [102, 101]
        System.out.println("Timeline(2): " + twitter.getTimeline(2)); // [203, 202, 201]

        // User 1 follows user 2
        twitter.follow(1, 2);

        // News feed (self + followees), most recent first
        System.out.println("NewsFeed(1): " + twitter.getNewsFeed(1)); // [203, 202, 201, 102, 101]

        // User 2 posts another tweet
        twitter.postTweet(2, 204);   // ts=6

        // News feed updates
        System.out.println("NewsFeed(1) after new tweet: " + twitter.getNewsFeed(1)); // [204, 203, 202, 201, 102, 101]

        // User 1 unfollows user 2
        twitter.unfollow(1, 2);

        // News feed now excludes user 2
        System.out.println("NewsFeed(1) after unfollow: " + twitter.getNewsFeed(1)); // [102, 101]

        // New user 3, follows 1 and 2, tests 10-cap behavior
        twitter.follow(3, 1);
        twitter.follow(3, 2);
        // Add more tweets for variety
        twitter.postTweet(1, 103);   // ts=7
        twitter.postTweet(2, 205);   // ts=8
        twitter.postTweet(2, 206);   // ts=9
        twitter.postTweet(1, 104);   // ts=10
        twitter.postTweet(1, 105);   // ts=11
        twitter.postTweet(2, 207);   // ts=12

        System.out.println("Timeline(1): " + twitter.getTimeline(1)); // last 10 of user 1
        System.out.println("NewsFeed(3): " + twitter.getNewsFeed(3)); // last 10 across 1 & 2
    }
}

class Tweet {
    int userId;
    int tweetId;
    long timestamp;

    Tweet(int userId, int tweetId, long timestamp) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.timestamp = timestamp;
    }
}
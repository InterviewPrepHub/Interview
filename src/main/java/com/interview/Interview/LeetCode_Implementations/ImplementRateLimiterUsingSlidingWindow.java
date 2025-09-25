package com.interview.Interview.LeetCode_Implementations;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Rate limiting is implemented to control no of requests coming to a service or API
that can handle within a certain time frame.

Below is the example of java implementation of sliding window rate limiter.
This tracks request timestamp per client and allow request only if number of
requests in sliding window is below the limit.

-> You expect many repeated messages, but only a few need to be retained in memory (due to the 10-second window).
-> You want to evict old entries automatically to reduce memory usage.


use a combination of queue & set

queue -> store the log entries in the order of arrival
set -> tracks messages that were printed in the last 10 sec

Before processing a new message
    -> we evict entries that are older than 10 secs
    -> if message is not in set, it is allowed to print & is added to both set and queue

 */
public class ImplementRateLimiterUsingSlidingWindow {

    private final Queue<LogEntry> queue;
    private final Set<String> messageSet;

    public ImplementRateLimiterUsingSlidingWindow() {
        this.queue = new LinkedList<>();
        this.messageSet = new HashSet<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {

        //remove messages older than 10 sec
        while(!queue.isEmpty() && timestamp - queue.peek().timestamp >= 10) {
            LogEntry old = queue.poll();
            messageSet.remove(old.message);
        }

        if(!messageSet.contains(message)) {
            queue.add(new LogEntry(timestamp, message));    //{1,foo} {2,bar}
            messageSet.add(message); //{foo, bar}
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        ImplementRateLimiterUsingSlidingWindow logger = new ImplementRateLimiterUsingSlidingWindow();
        System.out.println(logger.shouldPrintMessage(1, "foo"));   // true
        System.out.println(logger.shouldPrintMessage(2, "bar"));   // true
        System.out.println(logger.shouldPrintMessage(3, "foo"));   // false
        System.out.println(logger.shouldPrintMessage(8, "bar"));   // false
        System.out.println(logger.shouldPrintMessage(10, "foo"));  // false
        System.out.println(logger.shouldPrintMessage(11, "foo"));  // true

    }


}

class LogEntry {
    int timestamp;
    String message;

    LogEntry(int timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}

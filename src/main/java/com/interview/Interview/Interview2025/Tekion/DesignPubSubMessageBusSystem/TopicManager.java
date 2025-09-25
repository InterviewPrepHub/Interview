package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//Managing topic creation and deletion by singleton TopicManager
public class TopicManager {

    private static TopicManager instance;
    private ConcurrentMap<String, Topic> topics = new ConcurrentHashMap<>();

    public static  TopicManager getInstance() {
        if (instance == null) instance = new TopicManager();
        return instance;
    }

    public void createTopic(String name, int partitions) {
        topics.putIfAbsent(name, new Topic(name, partitions));
    }

    public void deleteTopic(String name) {
        topics.remove(name);
    }

    public Topic getTopic(String name) {
        return topics.get(name);
    }
}

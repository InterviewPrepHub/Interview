package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

public class Message {

    private String key;
    private String value;
    private long timestamp;

    // how can we  know message belong to which partition
    private int pId;
    private int topicId;

    public Message(String key, String value, long timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Message(String key, String value, long timestamp, int pId, int topicId) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.pId = pId;
        this.topicId = topicId;
    }
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

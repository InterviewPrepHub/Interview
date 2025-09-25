package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

public class TopicPartition {

    private String topicName;
    private int partitionIndex;

    public TopicPartition(String topicName, int partitionIndex) {
        this.topicName = topicName;
        this.partitionIndex = partitionIndex;
    }

    // Override equals and hashCode for use as a map key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicPartition that = (TopicPartition) o;
        return partitionIndex == that.partitionIndex && topicName.equals(that.topicName);
    }


    @Override
    public int hashCode() {
        return 31 * topicName.hashCode() + partitionIndex;  //31 is a prime number to avoid collision
    }
}

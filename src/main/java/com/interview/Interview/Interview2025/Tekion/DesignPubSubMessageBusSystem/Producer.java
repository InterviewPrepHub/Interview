package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

import org.interview.prep.code.Interview2025.Tekion.DesignPubSubMessageBusSystem.Partition;
import org.interview.prep.code.Interview2025.Tekion.DesignPubSubMessageBusSystem.TopicManager;

public class Producer {

    public void send(String topicName, String key, String value) {
        Topic topic = TopicManager.getInstance().getTopic(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("Topic not found: " + topicName);
        }
        int partitionIndex = Math.abs(key.hashCode()) % topic.getPartitionCount();
        Partition partition = topic.getPartition(partitionIndex);
        partition.addMessage(new Message(key, value, System.currentTimeMillis()));
    }
}


/*
key associated with message used for partitioning
value - actual message
 */
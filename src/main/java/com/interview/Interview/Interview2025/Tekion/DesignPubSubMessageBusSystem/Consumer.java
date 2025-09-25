package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

import org.interview.prep.code.Interview2025.Tekion.DesignPubSubMessageBusSystem.Partition;
import org.interview.prep.code.Interview2025.Tekion.DesignPubSubMessageBusSystem.TopicManager;
import org.interview.prep.code.Interview2025.Tekion.DesignPubSubMessageBusSystem.TopicPartition;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Consumer {

    private ConcurrentMap<TopicPartition, Integer> offsets = new ConcurrentHashMap<>();
    private Set<String> subscribedTopics = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public void subscribe(String topicName) {
        subscribedTopics.add(topicName);
    }

    public List<Message> poll() {
        List<Message> messages = new ArrayList<>();
        //iterate through all topics
        for (String topicName : subscribedTopics) {
            Topic topic = TopicManager.getInstance().getTopic(topicName);

            //iterate through all partitions
            for (int i = 0; i < topic.getPartitionCount(); i++) {
                TopicPartition tp = new TopicPartition(topicName, i);

                //how does consumer know which partition it is calling

                //get current offset
                int currentOffset = offsets.getOrDefault(tp, 0);
//                System.out.println("currentOffset : " + currentOffset);

//                partition table has topic id and tpic table has topic id  so now how will consumer know which partition belong to which topic

                Partition partition = topic.getPartition(i);

//                if (!partition.getTopicName().equals(topicName)) {
//                    throw new IllegalStateException("Partition " + i + " does not belong to topic " + topicName);
//                }


                //get new message from that partition offset
                //Return an empty list if the offset is beyond the last message
                //Otherwise, return a sublist of messages starting from the given offset to the end of the messages list

                Partition res = topic.getPartition(i);
//                res.addMessage(new Message("key", "value", System.currentTimeMillis(), res.getPartitionById(i), res.getTopicId()));

                /*List<Message> newMessages = topic.getPartition(i);
//                        .getMessagesFromOffset(currentOffset);
                messages.addAll(newMessages);
                System.out.println("size : "+newMessages.size());
                offsets.put(tp, currentOffset + newMessages.size());*/

            }
        }
        return messages;
    }
}


//what could the interviewer

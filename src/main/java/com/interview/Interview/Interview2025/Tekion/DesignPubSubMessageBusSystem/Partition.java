package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Partition {

    private int pId;

    private int topicId;

    private CopyOnWriteArrayList<Message> messages = new CopyOnWriteArrayList<>(); // thread safe read and write on arraylist

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessagesFromOffset(int offset) {
        System.out.println("offset is " + offset);
        System.out.println("messages size is " + messages.size());
        return offset >= messages.size() ? Collections.emptyList() : messages.subList(offset, messages.size());
    }


    public void setpId(int pId) {
        this.pId = pId;
    }

//    public Partition getPartitionById(int partitionId) {
//        return pId;
//    }


    public int getTopicId() {
        return topicId;
    }
}


package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

import java.util.List;

public class InMemeoryKafka {

    public static void main(String[] args) {

        System.out.println("Starting the in-memory message bus demo...");
        System.out.println();

        // 1. Get the singleton TopicManager instance and create a topic
        System.out.println("Creating topic 'orders' with 3 partitions...");
        TopicManager manager = TopicManager.getInstance();
        manager.createTopic("orders", 3);
        System.out.println("Topic 'orders' created successfully with 3 partitions");

        // 2. Produce a message to the topic
        System.out.println("Producing a message to the 'orders' topic...");
        Producer producer = new Producer();
        producer.send("orders", "order1", "Payment processed");
        System.out.println("Message produced: key='order1', value='Payment processed'");

        // 3. Consume messages from the topic
        System.out.println("Consuming messages from the 'orders' topic...");
        Consumer consumer = new Consumer();
        consumer.subscribe("orders");
        System.out.println("Consumer subscribed to the 'orders' topic.");

        List<Message> messages = consumer.poll();
        System.out.println("Polled " + messages.size() + " message(s) from 'orders' topic.");

        // 4. Print the details of each consumed message
        for (Message message : messages) {
            System.out.println("Consumed message: key=" + message.getKey() +
                    ", value=" + message.getValue() +
                    ", timestamp=" + message.getTimestamp());
        }

        System.out.println();
        System.out.println("Demo complete.");

    }
}


//why do we have a singleton TopicManager
package com.interview.Interview.Interview2025.Tekion.DesignPubSubMessageBusSystem;

import org.interview.prep.code.Interview2025.Tekion.DesignPubSubMessageBusSystem.Consumer;

public class Dummy {
    public static void main(String[] args) {
        Consumer c1 = new Consumer();
        c1.poll();

        Consumer c2 = new Consumer();
        c2.poll();
    }
}

package com.interview.Interview.Interview2024.Arcesium;

import java.util.concurrent.Semaphore;

public class SempahoreTest {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(3);

        new Thread(() -> {
            try {
                s.acquire();
                //do something critical
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                s.release();
            }
        });
    }
}

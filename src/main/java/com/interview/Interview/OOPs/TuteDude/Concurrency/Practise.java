package com.interview.Interview.OOPs.TuteDude.Concurrency;

public class Practise {

    public static void main(String[] args) {
        Task task = new Task();
        task.start();
    }
}

class Task extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
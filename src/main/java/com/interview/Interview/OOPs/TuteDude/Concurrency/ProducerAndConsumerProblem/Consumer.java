package com.interview.Interview.OOPs.TuteDude.Concurrency.ProducerAndConsumerProblem;

import java.util.List;

public class Consumer implements Runnable {

    List<Integer> product_list = null;
    int limit = 5;
    int product_no;

    public Consumer(List<Integer> product_list) {
        this.product_list = product_list;
    }

    public void takeProduct() {

        synchronized (product_list) {
            while (product_list.isEmpty()) {
                System.out.println("Product list is empty, waiting for producer to produce");
                try {
                    product_list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (product_list) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Product consumed " +product_list.remove(0));
            product_list.notify();
        }

    }


    @Override
    public void run() {

    }
}

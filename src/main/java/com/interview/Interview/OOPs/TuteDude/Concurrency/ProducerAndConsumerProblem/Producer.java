package com.interview.Interview.OOPs.TuteDude.Concurrency.ProducerAndConsumerProblem;

import java.util.List;

public class Producer implements Runnable{

    List<Integer> product_list = null;
    int limit = 5;
    int product_no;

    public Producer(List<Integer> product_list) {
        this.product_list = product_list;
    }

    public void seeProduct(int product_no) {

        synchronized (product_list) {
            while (product_list.size() == limit) {
                System.out.println("Product list is full, waiting for consumer to consume");
                try {
                    product_list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (product_list) {
            System.out.println("New product " + product_no);
            product_list.add(product_no);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            product_list.notify();

        }


    }

    @Override
    public void run() {
        while(true) {
            try {
                seeProduct(product_no);
                product_no++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.interview.Interview.OOPs.TuteDude.Concurrency;

public class PrintEvenOddNumbers {

    int start = 1;
    int end = 10;

    public static void main(String[] args) {
        /*PrintEvenOddNumbers obj = new PrintEvenOddNumbers();

        Thread t1 = new Thread(obj::printOdd(), "t1");
        Thread t2 = new Thread(obj::printEven(), "t2");

        t1.start();
        t2.start();*/

    }

    private void printEven() {
        synchronized (this) {
            while(start<=end) {
                if(start % 2 != 0) {    //if odd then wait
                    try{
                        wait();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName()+"-"+end);
                    end--;
                    notify(); // notify the odd thread
                }
            }
        }
    }

    private void printOdd() {
        synchronized (this) {
            while(start <= end) {
                if (start %2==0) { //if even then wait
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName()+"-"+ start);
                    start++;
                    notify(); // notify the even thread
                }
            }
        }
    }
}


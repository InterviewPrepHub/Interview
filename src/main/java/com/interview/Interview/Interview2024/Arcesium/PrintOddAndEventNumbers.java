package com.interview.Interview.Interview2024.Arcesium;

public class PrintOddAndEventNumbers {
    static int counter = 1;
    static int N = 10;

    public static void main(String[] args) {
        PrintOddAndEventNumbers p = new PrintOddAndEventNumbers();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                p.printEvenNumber();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                p.printOddNumber();
            }
        });

        t1.start();
        t2.start();
    }

    /*
    If the counter is even in the Thread T1, then wait for the thread T2 to print that even number.
    Otherwise, print that odd number, increment the counter and notify to the Thread T2 using the function notify().

    If the counter is odd in the Thread T2, then wait for the thread T1 to print that odd number.
    Otherwise, print that even number, increment the counter and notify the Thread T1 using the function notify().
     */

    // Function to print even numbers
    public void printEvenNumber() {
        synchronized (this) {
            // Print number till the N
            while (counter < N) {

                // If count is odd then print
                while (counter % 2 == 1) {

                    // Exception handle
                    try {
                        System.out.println("Thread T1 is waiting");
                        wait();
                    }
                    catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Thread T1 is in action: " + counter);
                // Print the number
                System.out.println(counter + " ");

                // Increment counter
                counter++;

                // Notify to 2nd thread
                notify();
            }
        }
    }

    public void printOddNumber() {
        synchronized (this) {
            // Print number till the N
            while (counter < N) {

                // If count is even then print
                // while loop checks if the current number is even. If it is, the thread waits using wait(), releasing the lock.
                while (counter % 2 == 0) {

                    // Exception handle
                    try {
                        System.out.println("Thread T1 is waiting");
                        wait();
                    }
                    catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Thread T2 is in action: " + counter);
                // Print the number
                System.out.println(counter + " ");

                // Increment counter
                counter++;

                // Notify to second thread - wake up the waiting thread (which should be printEvenNumber).
                notify();
            }
        }
    }

}

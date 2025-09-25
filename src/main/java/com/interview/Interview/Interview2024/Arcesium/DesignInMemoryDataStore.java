package com.interview.Interview.Interview2024.Arcesium;

/*
Design an in-memory reader, writer and the data store for the above conditions.

Writer keeps writing elements to a data store. Has to wait until data store has space.
Datastore will have fixed capacity.
Reader keeps reading the elements from data store in order it is written.
Element needs to be removed from the data store after is read

there should be thread safety with synchronised above
Also the reader and writer should happen synchronously
 */

import java.util.LinkedList;
import java.util.Queue;

public class DesignInMemoryDataStore {

    Queue<String> dataStore;
    int capacity;

    DesignInMemoryDataStore(int capacity) {
        this.capacity = capacity;
        dataStore = new LinkedList<>();
    }

    public synchronized String read() { //what is the difference in synchronized method and synchronized block like synchronized(this)
        while (dataStore.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String element = dataStore.poll();
        notifyAll();    //why use notifyAll() and not notify()
        return element;
    }

    public synchronized void write(String element) {
        while (capacity == dataStore.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        dataStore.add(element);
        notifyAll();
    }

    public static void main(String[] args) {
        DesignInMemoryDataStore d = new DesignInMemoryDataStore(5);

        Thread writerThread = new Thread(new Writer(d));
        Thread readerThread = new Thread(new Reader(d));

        writerThread.start();
        readerThread.start();
    }
}

class Reader implements Runnable {

    DesignInMemoryDataStore d;

    Reader(DesignInMemoryDataStore d) {
        this.d = d;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {
                String output = d.read();
                System.out.println("Reader read : "+output);
            }
        } catch (Exception e) {

        }
    }
}

class Writer implements Runnable {

    DesignInMemoryDataStore d;

    Writer(DesignInMemoryDataStore d) {
        this.d = d;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {      //can we use the data stream logic here where elements are given in a stream
                String input = "input "+i;
                d.write(input);
                System.out.println("Writer wrote : "+input);
            }
        } catch (Exception e) {

        }
    }
}


















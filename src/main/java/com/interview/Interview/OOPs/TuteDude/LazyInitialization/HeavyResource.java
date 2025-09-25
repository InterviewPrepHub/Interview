package com.interview.Interview.OOPs.TuteDude.LazyInitialization;

public class HeavyResource {

    public HeavyResource() {
        // Simulate heavy resource initialization
        try {
            Thread.sleep(3000); // Simulating a delay
            System.out.println("Heavy Resource loaded");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class LazyInitialization {
    private HeavyResource heavyResource;

    public HeavyResource getInstance() {
        if (heavyResource == null) {
            heavyResource = new HeavyResource();    //created only when called
        }
        return heavyResource;
    }
}

class Main {
    public static void main(String[] args) {
         System.out.println("Start application!");
         HeavyResource heavyResource = new HeavyResource();
         System.out.println("Application started...");

        //start application, then 3 sec delay and resource is loaded
        //and application started immediately

        System.out.println("*****************************");
        System.out.println("Start application");
        LazyInitialization lazyInitialization = new LazyInitialization();
        System.out.println("Application started!");
        //resource is only created now
        lazyInitialization.getInstance();

        //application started immediately, 3 sec delay and resource loaded
        //so start time of application is faster
        //hence Lazy initialization delays object creation until it’s actually needed
    }
}
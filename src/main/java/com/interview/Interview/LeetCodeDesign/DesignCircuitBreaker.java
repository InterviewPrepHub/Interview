package com.interview.Interview.LeetCodeDesign;

public class DesignCircuitBreaker {

    enum State {

        OPEN,       //All requests are blocked
        CLOSED,     //All requests go through
        HALF_OPEN   //few test requests are allowed
    }

    private State currState = State.CLOSED;     //curr state of circuit -> starts as closed
    private final int failureThreshold;      //max no of allowed failures before it moves to OPEN
    private final long retryTimePeriod;     //how long to wait (in millisec) before trying again after OPEN
    private int failureCount = 0;   //keeps track of how many failures happened consecutively
    private long lastFailureTime = 0;    //time when the last failure occurred

    public DesignCircuitBreaker(int failureThreshold, long retryTimePeriod) {
        this.failureThreshold = failureThreshold;
        this.retryTimePeriod = retryTimePeriod;
    }

    private void callService() {
        // if current state is OPEN, then check if timeout has passed
        if (currState == State.OPEN) {
            long currentTime = System.currentTimeMillis();

            //if enough time has passed then move to HALF_OPEN and retry again
            if (currentTime - lastFailureTime > retryTimePeriod) {
                currState = State.HALF_OPEN;
            } else {
                //otherwise , block request
                System.out.println("Circuit is OPEN, block request");
                return;
            }
        }

        //try calling external service
        try {
            stimulateExternalCall();    //70% chance of failure

            //if call succeeded, reset state
            onSuccess();
        } catch (Exception e) {
            //if call failed, update state
            onFailure();
        }
    }

    //get a fetcher to fetch curr state

    private State getCurrState() {
        return currState;
    }

    //stimulate calling an external system(with random failures)
    private void stimulateExternalCall() throws Exception {
        double random = Math.random();

        // 70% chance that this call fails
        if(random > 0.7) {
            throw new Exception("Simulated service failure");
        } else {
            System.out.println("Call succeeds");
        }
    }

    //Method that runs when call is successful
    private void onSuccess() {
        if (currState == State.HALF_OPEN || currState == State.CLOSED) {
            System.out.println("Service call succeeded");

            //reset failure count
            failureCount = 0;

            //Move back to CLOSE state
            currState = State.CLOSED;
        }
    }

    //method that runs when call fails
    private void onFailure() {

        //Increase failure count
        failureCount++;

        //update last failure time to now
        lastFailureTime = System.currentTimeMillis();

        System.out.println("Service call failed. Count = "+failureCount);

        //if we exceed the threshold , OPEN the circuit
        if (failureCount >= failureThreshold) {
            currState = State.OPEN;
            System.out.println("Circuit moved to OPEN state");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a circuit breaker that allows 3 failures, then waits 5s to retry
        DesignCircuitBreaker cb = new DesignCircuitBreaker(3, 5000);

        // Simulate calling the service 10 times with 1-second interval
        for (int i = 0; i < 10; i++) {
            cb.callService();
            Thread.sleep(1000);
        }

        // Wait 6 seconds (more than retry window) to test HALF_OPEN logic
        System.out.println("Waiting 6 seconds before retry...");
        Thread.sleep(6000);

        // Make one more call - this should go through in HALF_OPEN state
        cb.callService();
    }
}
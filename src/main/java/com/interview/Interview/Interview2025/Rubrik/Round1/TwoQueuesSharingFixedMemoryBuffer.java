package com.interview.Interview.Interview2025.Rubrik.Round1;

/*
Implement the below interface that supports two queues sharing a common fixed memory buffer
Queues can independently grow and shrink in size

Try to be reasonably memory efficient - if one queue not used, other queue should not be restricted to use the say only upto 50% of the buffer
 */
public class TwoQueuesSharingFixedMemoryBuffer {
}


class DoubleQueue {
    int[] buffer;
    DoubleQueue(int buffersize) {
        buffer = new int[buffersize];

    }

    // queueId = 0,1
    void push(int val, int queueId) {

    }

    // queueId = 0,1
    int pop(int queueId) {
        return -1;
    }
}

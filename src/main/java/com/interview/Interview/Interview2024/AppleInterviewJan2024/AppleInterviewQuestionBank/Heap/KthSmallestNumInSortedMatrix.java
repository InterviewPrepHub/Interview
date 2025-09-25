package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank.Heap;

import java.util.PriorityQueue;

public class KthSmallestNumInSortedMatrix {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 5, 7},
                {3, 7, 8},
                {4, 8, 9}
        };

        int k = 4;

        int result = kthSmallest(matrix, k);
        System.out.println(result);
    }

    private static int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        //create a min heap
        PriorityQueue<Pos> pq = new PriorityQueue<>((a,b) -> a.val - b.val);

        //add 1st value in each row in the heap
        for (int i = 0; i < rows; i++) {
            pq.add(new Pos(i,0, matrix[i][0]));
        }

        //iterate from 1 to k-1 and if current element in each row has next value in the same row then add to min heap
        //this ensures that heap will always have smalled unprocessed element
        for (int i = 0; i < k-1; i++) {
            Pos top = pq.poll();

            int r = top.i;
            int c = top.j;

            if(c < cols-1) {
                pq.add(new Pos(r,c+1, matrix[r][c+1]));
            }
        }

        return pq.poll().val;
    }
}

class Pos {
    int i;
    int j;
    int val;

    public Pos(int i, int j, int val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }
}

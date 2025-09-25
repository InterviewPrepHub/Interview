package com.interview.Interview.Callicoder.BinarySearch;

/*
Write an efficient algorithm that searches for a value in an m x n matrix. The matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,  5,  9,  11],
  [13, 16, 19, 24],
  [28, 30, 38, 50]
]
target = 5
Output: true
Example 2:

Input:
matrix = [
  [1,  5,  9,  11],
  [13, 16, 19, 24],
  [28, 30, 38, 50]
]
target = 12
Output: false
 */

public class Sorted2DMatrixSearch {

    public static void main(String[] args) {

       int[][] matrix = {
               {1,  5,  9,  11},
               {13, 16, 19, 24},
               {28, 30, 38, 50}
       };

       int target = 15;
       int row = matrix.length; //3
       int col = matrix[0].length;  //3

       int low = 0, high = row * col -1; // low = 0, high = 11

       while (low<=high) {
           int mid = (low+high)/2;  //5

           int midValue = matrix[mid/col][mid%col]; //matrix[5/4][5%4] = matrix[1][1]

           if(target == midValue) {
               System.out.println(true);
               break;
           } else if (target < midValue) {
               high = mid-1;    //we move left to lower indices
           } else {
               low = mid+1;     //we move right to higher indices
           }
       }

    }
}

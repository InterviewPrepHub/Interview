package com.interview.Interview.Callicoder.BinarySearch;

/*
Given an m x n matrix and a target value, find the position of the target value in the matrix if it is present in it. Otherwise, print “Not Found”. In the given matrix, every row and column is sorted in increasing order.

Example 1:

Input: matrix[4][4] = { {10, 20, 30, 40},
                      {15, 25, 35, 45},
                      {27, 29, 37, 48},
                      {32, 33, 39, 50}};
              target = 29
Output: Found at (2, 1)
Explanation: Element at (2,1) is 29
 */
public class SearchInRowAndColumnWiseSortedMatrix {

    public static void main(String[] args) {
        int matrix[][] = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}};

        int target = 29;

        int row = matrix.length;
        int col = matrix[0].length;

        int i = 0;
        int j = col - 1;

        while (i >= 0 && i < row && j >= 0 && j < col) {
            if (target == matrix[i][j]) {
                System.out.println(i+","+j);
                break;
            } else if (target < matrix[i][j]) {
                j--;
            } else {
                i++;
            }
        }

    }
}

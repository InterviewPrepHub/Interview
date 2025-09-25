package com.interview.Interview.Interview2024.AppleInterviewJan2024.AppleInterviewQuestionBank;

public class BinarySearchIn2DArray {

    public static void main(String[] args) {
        int[][] arr = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50}
        };

        int k = 29;

        searchForKInMatrix(arr, k);
    }

    private static boolean searchForKInMatrix(int[][] arr, int target) {

        int rows = arr.length;
        int cols = arr[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while(left <= right) {
            int mid = left + (right-left)/2;
            int midValue = arr[mid/cols][mid%cols];

            if(midValue==target) {
                return true;
            } else if(midValue < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }

        }
        return false;
    }


}

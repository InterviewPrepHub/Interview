package com.interview.Interview.Callicoder.BinarySearch;

/*
Given an array of lowercase letters sorted in ascending order, and a target letter,
find the smallest letter in the array that is greater than the target.

Note that, Letters also wrap around. For example, if target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Example 1:

Input: letters = ["d", "h", "l"], target = "a"
Output: "d"
Example 2:

Input: letters = ["d", "h", "l"], target = "d"
Output: "h"
Example 3:

Input: letters = ["d", "h", "l"], target = "f"
Output: "h"
Example 4:

Input: letters = ["d", "h", "l"], target = "j"
Output: "l"
Example 4:

Input: letters = ["d", "h", "l"], target = "n"
Output: "d"
 */
public class NextLetter {

    public static void main(String[] args) {

        char letters[] = {'d','g','h','l'}, target = 'e';
        int low = 0, high = letters.length-1;
        int res = 0;

        while (low <= high) {
            int mid = low+(high-low)/2;
            if(letters[mid] > target) {
                res = mid;
                high = mid - 1;
            }
            if (letters[mid] < target) {
                low = mid+1;
            }

        }
        System.out.println(res);
    }
}

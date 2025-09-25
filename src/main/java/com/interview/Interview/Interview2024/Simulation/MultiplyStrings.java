package com.interview.Interview.Interview2024.Simulation;

import java.util.List;

public class MultiplyStrings {

    public static void main(String[] args) {
        MultiplyStrings t = new MultiplyStrings();
//        System.out.println(t.multiply("2", "2"));

//        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
//        t.spiralOrder(arr);

//        String n1 = "11";
//        String n2 = "1";
//        t.addBinary(n1, n2);

        t.addDigits(561);
    }

    public String multiply(String num1, String num2) {
        return String.valueOf(Integer.parseInt(num1) * Integer.parseInt(num2));
    }

    public /*List<Integer>*/void spiralOrder(int[][] matrix) {

        int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;

        while(top <= bottom && left <= right) {

            //traverse top row
            for (int i = left; i <= right ; i++) {
                System.out.println(matrix[top][i]); //1,2,3
            }
            top++;  //1

            //traverse right col
            //2nd row, traverse last col
            for (int i = top; i <=bottom ; i++) {
                System.out.println(matrix[i][right]);
            }
            right--;

            //3rd/last row, 2nd last col to 1st col
            if(top <= bottom) {
                for (int i = right; i >=left ; i--) {
                    System.out.println(matrix[bottom][i]);
                }
                bottom--;
            }

            //1st col,2nd row
            if(left <= right) {
                for (int i = bottom; i >= top ; i--) {
                    System.out.println(matrix[i][left]);
                }
                left++;
            }


        }
    }

    public String addBinary(String n1, String n2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int a = n1.length()-1;
        int b = n2.length()-1;

        while (a >= 0 || b >= 0 || carry > 0) {
            int sum = carry;    //0,1

            if (a >= 0) {
                sum += n1.charAt(a) - '0';
                a--;
            }

            if (b >= 0) {
                sum += n2.charAt(b) - '0';
                b--;
            }

            sb.insert(0, sum%2); //calculates the remainder after dividing by 2 gives the current bit in the sum.
            carry = sum/2;  //gives carry
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    public int addDigits(int num) {
       if(num < 10) return num;
       int sum = 0;

       while(num != 0) {
           sum  = sum + num % 10;
           num = num /10;
       }
       System.out.println(sum);
       return sum;
    }
}

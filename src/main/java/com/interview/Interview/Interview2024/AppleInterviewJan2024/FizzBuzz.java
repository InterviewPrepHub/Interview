package com.interview.Interview.Interview2024.AppleInterviewJan2024;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FizzBuzz {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.fizzBuzz(n);

        bufferedReader.close();
    }
}

class Result {

    public static void fizzBuzz(int n) {

        for (int i = 1; i <= n; i++) {

            if(n%3 == 0 && n%5 !=0) {
                System.out.println("Fizz");
            }
            else if(n%3 != 0 && n%5 == 0) {
                System.out.println("Buzz");
            }
            else if(n%3 ==0 && n%5 == 0) {
                System.out.println("FizzBuzz");
            }
            else {
                System.out.println(i);
            }
        }


    }

}

/*

Consider following definition of Binary search tree

class BSTNode {
    public int value;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(int value, BSTNode left, BSTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

A List of BSTNode Objects is called perfectly contained BST if and only if 3 following rules are satisfied
        1. the nodes in the list belong to one and only one BST
        2.All nodes in the list belong to the BST
        3. All nodes in the BST belong to an array
        

Implement a function findRoot, that given a list of BSTNode objects, return the value of the root of the BST, if the list is perfectly contained BST or -1 otherwise


        import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;



class Result {

    */
/*
     * Complete the 'findRoot' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY nodes as parameter.
     *//*


    public static int findRoot(List<List<Integer>> nodes) {
        // Write your code here

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int nodesRows = Integer.parseInt(bufferedReader.readLine().trim());
        int nodesColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> nodes = new ArrayList<>();

        IntStream.range(0, nodesRows).forEach(i -> {
            try {
                nodes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.findRoot(nodes);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}





    Can you solve the method findRoot for below question

        Consider following definition of Binary search tree

class BSTNode {
    public int value;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(int value, BSTNode left, BSTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

    A List of BSTNode Objects is called perfectly contained BST if and only if 3 following rules are satisfied
        1. the nodes in the list belong to one and only one BST
        2.All nodes in the list belong to the BST
        3. All nodes in the BST belong to an array


        Implement a function findRoot, that given a list of BSTNode objects, return the value of the root of the BST, if the list is perfectly contained BST or -1 otherwise


        import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;



class Result {

    */
/*
     * Complete the 'findRoot' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY nodes as parameter.
     *//*


    public static int findRoot(List<List<Integer>> nodes) {
        // Write your code here

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int nodesRows = Integer.parseInt(bufferedReader.readLine().trim());
        int nodesColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> nodes = new ArrayList<>();

        IntStream.range(0, nodesRows).forEach(i -> {
            try {
                nodes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.findRoot(nodes);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
*/

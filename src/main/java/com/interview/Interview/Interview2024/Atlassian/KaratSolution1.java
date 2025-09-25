package com.interview.Interview.Interview2024.Atlassian;

/*
We are working on a security system for a badged-access room in our company's building.

Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:

1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)

2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)

Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.

records1 = [
  ["Paul",     "enter"],
  ["Pauline",  "exit"],
  ["Paul",     "enter"],
  ["Paul",     "exit"],
  ["Martha",   "exit"],
  ["Joe",      "enter"],
  ["Martha",   "enter"],
  ["Steve",    "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "enter"],
  ["Joe",      "enter"],
  ["Curtis",   "exit"],
  ["Curtis",   "enter"],
  ["Joe",      "exit"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "exit"],
  ["Joe",      "enter"],
  ["Joe",      "enter"],
  ["Martha",   "exit"],
  ["Joe",      "exit"],
  ["Joe",      "exit"]
]

Expected output: ["Steve", "Curtis", "Paul", "Joe"], ["Martha", "Pauline", "Curtis", "Joe"]

Other test cases:

records2 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
]

Expected output: [], []

records3 = [
  ["Paul", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
]

Expected output: ["Paul"], ["Paul"]


record4[0] -> emp, record4[1] -> action

Set1 = insideRoom
Set2 = unMatchedExist

action - enter, exit
records4 = [
  ["Raj", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
  ["Paul", "enter"],
  ["Raj", "enter"],
]


Expected output: ["Raj", "Paul"], ["Paul"]

All Test Cases:
mismatches(records1) => ["Steve", "Curtis", "Paul", "Joe"], ["Martha", "Pauline", "Curtis", "Joe"]
mismatches(records2) => [], []
mismatches(records3) => ["Paul"], ["Paul"]
mismatches(records4) => ["Raj", "Paul"], ["Paul"]

n: length of the badge records array
*/

import java.io.*;
import java.util.*;
//import javafx.util.Pair;

public class KaratSolution1 {
    public static void main(String[] argv) {

        String[][] records1 = {
                {"Paul", "enter"},
                {"Pauline", "exit"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"},
                {"Martha", "enter"},
                {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"},
                {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"},
        };

        String[][] records2 = {
                {"Paul", "enter"},
                {"Paul", "exit"},
        };

        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
        };

        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"},
        };


        List<List<String>> res = mismatches(records3);

        for(List<String> r : res) {
            System.out.println(r);
        }

    }

//   All Test Cases:
// mismatches(records1) => ["Steve", "Curtis", "Paul", "Joe"], ["Martha", "Pauline", "Curtis", "Joe"]
// mismatches(records2) => [], []
// mismatches(records3) => ["Paul"], ["Paul"]
// mismatches(records4) => ["Raj", "Paul"], ["Paul"]


    public static List<List<String>> mismatches(String[][] records) {

        Set<String> insideRoom = new HashSet<String>();
        Set<String> unmatchExit = new HashSet<String>();

    /*
    String[][] records2 = {
      {"Paul", "enter"},
      {"Paul", "exit"},
    };

    String[][] records3 = {
      {"Paul", "enter"},
      {"Paul", "enter"},
      {"Paul", "exit"},
      {"Paul", "exit"},
    };

    */
        for(String[] record : records) {
            String empName = record[0];
            String action = record[1];

            if(action.equalsIgnoreCase("enter")) {
                // if(!insideRoom.contains(empName)) {
                //   insideRoom.add(empName);
                // } else {
                //   unmatchExit.add(empName);
                // }

                if(insideRoom.contains(empName)) {
                    unmatchExit.add(empName);
                } else {
                    insideRoom.add(empName);
                }
            } else if(action.equalsIgnoreCase("exit")) {
                if(insideRoom.contains(empName)) {
                    insideRoom.remove(empName);
                } else {
                    unmatchExit.add(empName);
                }
            }
        }
        List<String> noExit = new ArrayList<>(insideRoom);
        List<String> noEntry = new ArrayList<>(unmatchExit);

        noEntry.addAll(unmatchExit);

        List<List<String>> res = new ArrayList<>();
        res.add(noExit);
        res.add(noEntry);

        return res;
    }
}


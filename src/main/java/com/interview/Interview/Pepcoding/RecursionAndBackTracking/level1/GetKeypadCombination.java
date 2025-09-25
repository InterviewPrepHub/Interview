package com.interview.Interview.Pepcoding.RecursionAndBackTracking.level1;

import java.util.ArrayList;


/*

Method      |   Time Complexity |    Space Complexity                When to Use
getKPC()    |    O(k^n)         |    O(n * total_combinations)       When you need all combinations returned for later use
printKPC()  |    O(k^n)         |    O(n) (recursion stack only)     When you just want to print or process on-the-fly

 */
public class GetKeypadCombination {

    public static void main(String[] args) {
        String input = "573"; //"mnop", "uv", "ghi"
        String[] codes = {"?!", "abc", "def", "ghi", "jkl", "mnop", "qrst", "uv", "wxyz", ".;"};
        ArrayList<String> words = getKPC(input, codes);
        System.out.println(words.size());

        //print keypad combinations
        printKPC(input, "", codes);

    }

    private static void printKPC(String input, String ans, String[] codes) {

        if (input.length() == 0) {
            System.out.println(ans);
            return;
        }
        char ch = input.charAt(0);
        String ros = input.substring(1);

        String codeforch = codes[ch-'0'];
        for (int i = 0; i < codeforch.length();i++) {
            char ch1 = codeforch.charAt(i);
            printKPC(ros, ans + ch1, codes);
        }
    }

    public static ArrayList<String> getKPC(String s, String[] codes) {
        if (s.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = s.charAt(0);  // e.g., '5'
        String ros = s.substring(1); // remaining string

        ArrayList<String> rres = getKPC(ros, codes);
        ArrayList<String> mres = new ArrayList<>();

        int idx = ch - '0';             //convert char to index
        String codesforch = codes[idx]; // get code string for this digit

        for (int i = 0; i < codesforch.length(); i++) {
            char chCode = codesforch.charAt(i);
            for (String res : rres) {
                mres.add(chCode + res);
            }
        }

        return mres;
    }
}


/*
✅ Time Complexity

Let’s denote:
	•	n = number of digits in the input string
	•	Let’s say k = average number of characters per digit (codes[i].length() on average)

The total number of combinations is:
k * k * k * ... (n times) = k^n
That’s because:
	•	At level 1 of recursion: you have k choices
	•	For each choice, you recurse down and again get k choices
	•	So, total calls ≈ k^n

👉 Time Complexity: O(k^n), where:
	•	k is average number of letters mapped per digit (say 3–4 on average)
	•	n is the length of the input string (number of digits)

⚡️ Space Complexity

There are two parts to this:
	1.	Recursion stack:
	•	Max depth = n (since 1 recursive call per digit)
	•	So, recursion space = O(n)
	2.	Result list mres:
	•	We store all combinations → total combinations = k^n
	•	Each string has length n
	•	So total space = O(n * k^n)

 */
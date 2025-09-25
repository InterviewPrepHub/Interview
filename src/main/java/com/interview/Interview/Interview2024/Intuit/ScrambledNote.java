package com.interview.Interview.Interview2024.Intuit;

import java.util.Arrays;
import java.util.List;

/*
You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice
question disguised as a random note.

Your task is to write a function that, given a list of words and a note, finds and returns the word in the list that
is scrambled inside the note, if any exists. If none exist, it returns the result "-" as a string. There will be at
most one matching word. The letters don't need to be in order or next to each other. The letters cannot be reused.

Example:
words = ["baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"]
note1 = "ctay"
find(words, note1) => "cat"   (the letters do not have to be in order)

note2 = "bcanihjsrrrferet"
find(words, note2) => "cat"   (the letters do not have to be together)

note3 = "tbaykkjlga"
find(words, note3) => "-"     (the letters cannot be reused)


All Test Cases:
find(words, note1) -> "cat"
find(words, note2) -> "cat"
find(words, note3) -> "-"
find(words, note4) -> "baby"
 */

public class ScrambledNote {

    public static String find(List<String> words, String note) {
        // Convert the note string to a character array for easier manipulation
        char[] noteChars = note.toCharArray();

        // Iterate through each word in the list
        for (String word : words) {
            // Convert the word string to a character array
            char[] wordChars = word.toCharArray();

            // Create a copy of the note character array to track which characters are matched
            char[] noteCopy = Arrays.copyOf(noteChars, noteChars.length);

            // Check if the word can be formed from the note characters
            if (canFormWord(noteCopy, wordChars)) {
                return word; // Return the word if it can be formed
            }
        }

        return "-"; // Return "-" if no matching word is found
    }

    // Helper method to check if a word can be formed from a set of characters
    private static boolean canFormWord(char[] note, char[] word) {
        // Iterate through each character in the word
        for (char ch : word) {
            boolean found = false;
            // Iterate through each character in the note to find a match
            for (int i = 0; i < note.length; i++) {
                if (note[i] == ch) {
                    found = true;
                    note[i] = '*'; // Mark the character as used
                    break;
                }
            }
            // If a character in the word is not found in the note, return false
            if (!found) {
                return false;
            }
        }
        // If all characters in the word are found in the note, return true
        return true;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz");
        String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "ybba";

        System.out.println(find(words, note1)); // Output: cat
        System.out.println(find(words, note2)); // Output: cat
        System.out.println(find(words, note3)); // Output: -
        System.out.println(find(words, note4)); // Output: baby
    }
}

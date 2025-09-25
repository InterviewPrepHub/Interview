package com.interview.Interview.LeetCodeDesign;

import java.util.ArrayList;
import java.util.List;

/*
Implement a TextWrapper class with a method wrap(String text, int maxWidth) which wraps the input text into multiple lines,
such that no line exceeds maxWidth characters. Words should not be broken across lines unless they are longer than maxWidth.
The method should return a list of strings — each representing a line.
 */
public class DesignTextWrapper {

    private int maxWidth;

    DesignTextWrapper(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public List<String> wrap(String text) {
        List<String> result = new ArrayList<>();
        if (text == null || text.isEmpty()) return result;

        String[] words = text.split("\\s+");    // here '\\s' -> whitespace, tab or newline and + -> one or more of //s
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (word.length() > maxWidth) {
                // Split the long word if it exceeds line width
                if (currentLine.length() > 0) {
                    result.add(currentLine.toString().trim());  //add the current line which already has some word to the result after removing trailing spaces
                    currentLine.setLength(0);       //clear the current line to start fresh
                }
                result.addAll(splitLongWord(word));
                continue;
            }

            //check if adding this word over flow the current line
            if (currentLine.length() + 1 + word.length() > maxWidth) {
                result.add(currentLine.toString().trim());
                currentLine.setLength(0);
            }

            //if current line is not empty then add space before the next word
            if (currentLine.length() > 0) {
                currentLine.append(" ");
                currentLine.append(word);
            }
        }

        //post loop add whatever is left in the current line
        if (currentLine.length() > 0) {
            result.add(currentLine.toString());
        }

        return result;
    }

    /*
    eg:
        word = "encyclopedia"
        maxWidth = 4

        parts = ["ency", "clop", "edia"]
     */
    private List<String> splitLongWord(String word) {
        List<String> parts = new ArrayList<>();
        int i = 0;
        while (i < word.length()) {
            //avoid going past the end of the word ,  avoid IndexOutOfBoundsException
            int width = Math.min(i + maxWidth, word.length());
            parts.add(word.substring(i, width));
            i += maxWidth;
        }
        return parts;
    }

    public static void main(String[] args) {
        DesignTextWrapper wrapper = new DesignTextWrapper(10);
        String input = "The quick brown fox jumps over the lazy dog";
        List<String> wrapped = wrapper.wrap(input);
        wrapped.forEach(System.out::println);
    }

}

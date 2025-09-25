package com.interview.Interview.LeetCodeDesign;

import java.util.ArrayList;
import java.util.List;

public class DesignTextJustifier {

    /*
        Adding Text Justification

        📌 Example — Without Justification (maxWidth = 16)
           Input: "The quick brown fox"

           Wrapped without justification:

            The quick brown
            fox

           Here, only one space is added between words. The lines are left-aligned, not stretched.

        📌 Example — With Justification
           Same input, but now we justify:

            The  quick brown
            fox
            Line 1 is exactly 16 characters wide

            Spaces are distributed between words to "stretch" the line

            Justification is typically applied to:

            -> All full lines (not the last line)
            -> Lines that contain multiple words
     */
    private List<String> justifyLine(String text, int maxWidth) {


        String[] words = text.split("\\s+");
        List<String> res = new ArrayList<>();
        int start = 0;

        while (start < words.length) {
            int width = words[start].length();
            int letters = width;
            int end = start;

            while (end + 1 < words.length && width + 1 + words[end + 1].length() <= maxWidth) {
                width = width + 1 + words[++end].length();
                letters = letters + words[end].length();
            }

            StringBuilder sb = new StringBuilder();
            sb.append(words[start++]);
            if (end == words.length - 1 || start > end) {
                while (start <= end) {
                    sb.append(' ');
                    sb.append(words[start++]);
                }
                while (sb.length() < maxWidth) sb.append(' ');
            } else {
                int baseSpace = (maxWidth - letters) / (end - start + 1);
                int bonusSpace = (maxWidth - letters) % (end - start + 1);
                while (start <= end) {
                    for (int i = 0; i < baseSpace; i++) sb.append(' ');
                    if (bonusSpace-- > 0) sb.append(' ');
                    sb.append(words[start++]);
                }
            }
            res.add(sb.toString());
        }
        return res;


    }

    public static void main(String[] args) {
        DesignTextJustifier wrapper = new DesignTextJustifier();
        String input = "The quick brown fox jumps over the lazy dog";
        int maxWidth = 10;
        List<String> wrapped = wrapper.justifyLine(input, maxWidth);
        wrapped.forEach(System.out::println);
    }
}

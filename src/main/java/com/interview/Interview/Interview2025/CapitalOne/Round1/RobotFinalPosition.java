package com.interview.Interview.Interview2025.CapitalOne.Round1;

/*

Robot Up/Down Commands:
A robot responds to two commands:

U: move one step up

D: move one step down
Given a string commands of U and D, determine the robot’s final position relative to the starting point.

Return "U" if it ends above the start.

Return "D" if it ends below the start.

Return "" (empty string) if it ends at the starting point.

Example:
commands = "UDDUDD" → Output: "D"
 */
public class RobotFinalPosition {

    // Returns "U" if above start, "" if same, "D" if below
    public static String solution(String commands) {
        if (commands == null || commands.isEmpty()) return "";

        int pos = 0; // net displacement from start
        for (int i = 0; i < commands.length(); i++) {
            char c = commands.charAt(i);
            if (c == 'U') pos++;
            else if (c == 'D') pos--;
            else {
                // ignore invalid chars (or throw if you prefer)
                // throw new IllegalArgumentException("Only U/D allowed");
            }
        }
        return pos > 0 ? "U" : (pos < 0 ? "D" : "");
    }

    // quick demo
    public static void main(String[] args) {
        System.out.println(solution("UDDUDD")); // "D"
        System.out.println(solution("DDDDUUUU")); // ""
        System.out.println(solution("UUUD")); // "U"
        System.out.println(solution("")); // ""
        System.out.println(solution(null)); // ""
    }
}


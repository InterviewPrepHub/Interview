package com.interview.Interview.Interview2025.Stripe.Round1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {

    public static void main(String[] args) {

        String input = "user123--idABC$$idXYZ--user456--idX";
        List<String> masterIds = List.of("idABC", "idXYZ", "idABCDEF", "idQRS", "idXRay");

        Result result = analyze(input, masterIds);
        result.print();

    }

    public static Result analyze(String inputString, List<String> masterIds) {
        // Step 1: Extract all valid IDs (e.g., "idABC")
        List<String> extractedIds = new ArrayList<>();
        Pattern pattern = Pattern.compile("id[A-Z]+");
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            extractedIds.add(matcher.group());
        }

        // Step 2: Use a HashSet for quick lookup of master IDs
        Set<String> masterIdSet = new HashSet<>(masterIds);

        // Step 3: Prepare result structures
        Map<String, Boolean> exactMatches = new HashMap<>();
        Map<String, List<String>> prefixMatches = new HashMap<>();

        for (String id : extractedIds) {
            // Check for exact match
            exactMatches.put(id, masterIdSet.contains(id));

            // Check for prefix match
            List<String> matchingPrefixes = new ArrayList<>();
            for (String masterId : masterIds) {
                if (masterId.startsWith(id) && !masterId.equals(id)) {
                    matchingPrefixes.add(masterId);
                }
            }
            prefixMatches.put(id, matchingPrefixes);
        }

        return new Result(extractedIds, exactMatches, prefixMatches);
    }
}

class Result {
    List<String> extractedIds;
    Map<String, Boolean> exactMatches;
    Map<String, List<String>> prefixMatches;

    public Result(List<String> extractedIds, Map<String, Boolean> exactMatches, Map<String, List<String>> prefixMatches) {
        this.extractedIds = extractedIds;
        this.exactMatches = exactMatches;
        this.prefixMatches = prefixMatches;
    }

    public void print() {
        System.out.println("Extracted IDs: " + extractedIds);
        System.out.println("Exact Matches: " + exactMatches);
        System.out.println("Prefix Matches: " + prefixMatches);
    }
}
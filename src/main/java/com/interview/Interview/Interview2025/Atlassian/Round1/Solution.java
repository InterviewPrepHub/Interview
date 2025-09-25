package com.interview.Interview.Interview2025.Atlassian.Round1;

/*
a one-way route.

Given input that provides the (directed) steps that each cart takes as pairs, write a function that identifies all
the start locations, and a collection of all of the possible ending locations for each start location.

A          E      J       Key:  [Origins]
  / \        / \      \                 \
 B   C      F   L      M             [Destinations]
  \ /  \   /
   K     G
        / \
       H   I

public class Solution {
  public static void main(String[] argv) {
    String[][] paths = {

//from - to
      {"B", "K"},
      {"C", "K"},
      {"E", "L"},
      {"F", "G"},
      {"J", "M"},
      {"E", "F"},
      {"C", "G"},
      {"A", "B"},
      {"A", "C"},
      {"G", "H"},
      {"G", "I"},
    };

  }

 */

import java.util.*;

public class Solution {

    public static void main(String[] argv) {
        String[][] paths = {
                {"B", "K"}, {"C", "K"}, {"E", "L"}, {"F", "G"}, {"J", "M"},
                {"E", "F"}, {"C", "G"}, {"A", "B"}, {"A", "C"}, {"G", "H"}, {"G", "I"},
        };

        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // Build adjacency list and track in-degrees
        for (String[] edge : paths) {
            String from = edge[0], to = edge[1];

            adj.putIfAbsent(from, new ArrayList<>());
            adj.get(from).add(to);

            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            inDegree.putIfAbsent(from, 0);
        }

        // Step 1: Find all start locations (in-degree = 0)
        List<String> startNodes = new ArrayList<>();
        for (String node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                startNodes.add(node);
            }
        }

        // Step 2: For each start node, do DFS to find end locations
        for (String start : startNodes) {
            Set<String> endLocations = new HashSet<>();
            dfs(start, adj, endLocations);
            System.out.println("Start: " + start + " → End Locations: " + endLocations);
        }
    }

    // DFS to find all end (leaf) nodes from this node
    private static void dfs(String node, Map<String, List<String>> adj, Set<String> result) {
        if (!adj.containsKey(node) || adj.get(node).isEmpty()) {
            result.add(node); // It's a leaf
            return;
        }

        for (String neighbor : adj.get(node)) {
            dfs(neighbor, adj, result);
        }
    }
}

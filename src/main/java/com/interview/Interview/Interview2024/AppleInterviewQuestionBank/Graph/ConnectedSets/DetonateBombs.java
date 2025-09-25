package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Graph.ConnectedSets;

import java.util.ArrayList;
import java.util.List;

/*
You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi
denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range.
These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.

Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.

 */

public class DetonateBombs {

    public static void main(String[] args) {
        DetonateBombs d = new DetonateBombs();

        int[][] bombs = {{2,1,3},{6,1,4}};
        int res = d.maximumDetonation(bombs);
        System.out.println(res);
    }

    public int maximumDetonation(int[][] bombs) {

        int s = bombs.length;
        List<Integer> adj[] = new ArrayList[s];

        for (int i = 0; i < s; i++) {
            adj[i] = new ArrayList<>();
            for (int j = 0; j < s; j++) {
                if (i != j) {
                    //find the distance between one bomb and its neighbouring bombs which can get detonated
                    int dist = calculateDistance(bombs[i][0], bombs[i][1], bombs[j][0], bombs[j][1]);
                    if(dist <= bombs[i][2]) {
                        adj[i].add(j);
                    }
                }
            }
        }

        int maxDetonate = 0;

        //for detonating each bomb, find max detonated bombs
        for(int i=0;i<s;i++) {
            int detonated = dfs(i,adj,new boolean[s]);
            maxDetonate = Math.max(maxDetonate, detonated);
        }

        return maxDetonate;
    }

    private int dfs(int i, List<Integer>[] adj, boolean[] visited) {
        if (visited[i]) {
            return 0;
        }

        visited[i] = true;
        int count = 1;

        for (int next : adj[i]) {
            count += dfs(next, adj, visited);
        }

        return count;
    }

    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}

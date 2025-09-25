package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Graph;

import java.util.*;

public class FindNthDegreeOfConnection {

    int v;
    ArrayList<Character>[] adj;

    public FindNthDegreeOfConnection(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(char v, char w) {
        adj[v - 'A'].add(w);
    }

    public static void main(String[] args) {
        int n = 5;
        FindNthDegreeOfConnection f = new FindNthDegreeOfConnection(n);
        f.addEdge('A', 'B');
        f.addEdge('A', 'D');
        f.addEdge('B', 'A');
        f.addEdge('B', 'C');
        f.addEdge('C', 'D');
        f.addEdge('C', 'B');
        f.addEdge('C', 'E');
        f.addEdge('D', 'A');
        f.addEdge('D', 'B');
        f.addEdge('E', 'C');

        List<Character> conn = f.findConnectionDegree('A', 2);
        System.out.println(conn);
    }

    private List<Character> findConnectionDegree(char start, int degree) {

        List<Character> result = new ArrayList<>();
        Queue<Character> queue = new LinkedList<>();
        boolean[] visited = new boolean[v];

        queue.add(start);
        visited[start - 'A'] = true;

        int currentDegree = 0;

        while (!queue.isEmpty() && currentDegree <= degree) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                char current = queue.poll();
                result.add(current);

                for (char neighbor : adj[current - 'A']) {
                    if (!visited[neighbor - 'A']) {
                        queue.add(neighbor);
                        visited[neighbor - 'A'] = true;
                    }
                }
            }

            currentDegree++;
        }

        return result;
    }
}

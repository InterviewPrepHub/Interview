package com.interview.Interview.Pepcoding.Graphs.Level1.BFS.TopologicalSort;

import java.util.ArrayList;
import java.util.Iterator;
/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

 */
public class CourseScheduleI {

    int v;
    ArrayList<Integer> adj[];

    public CourseScheduleI(int v) {
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    // Detects cycle, returns true if cycle exists
    private static boolean isCyclic(CourseScheduleI c, int src, boolean[] visited, boolean[] recStack) {
        if (recStack[src]) {
            return true;
        }
        if (visited[src]) {
            return false;
        }

        visited[src] = true;
        recStack[src] = true;

        Iterator<Integer> it = c.adj[src].iterator();
        while (it.hasNext()) {
            if (isCyclic(c, it.next(), visited, recStack)) {
                return true;
            }
        }

        recStack[src] = false;
        return false;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        CourseScheduleI c = new CourseScheduleI(numCourses);

        for (int[] pre : prerequisites) {
            c.addEdge(pre[1], pre[0]);  // b comes before a
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(c, i, visited, recStack)) {
                return false; // cycle detected
            }
        }

        System.out.println("check recStack");
        for (int i = 0; i < recStack.length; i++) {
            System.out.println(recStack[i]);
        }
        System.out.println();
        return true; // no cycle detected
    }

    // quick demo
    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}}));  // true -> visited [true, true]  & onPath [false, false]
//        System.out.println(canFinish(2, new int[][]{{1,0},{0,1}}));   // false -> visited [true, true]  & onPath [true, true] ->
        // When traversing, if you hit a node already in recStack, a cycle is found.
    }
}

    /*
    First, the problem

        - We want to know if all courses can be finished given prerequisites.
        - This boils down to: is the graph acyclic?
        - If there’s a cycle (like 0 → 1 → 0), you can’t finish all courses.

    What are the two arrays?

    visited[]
        - Marks nodes that we’ve completely processed.
        - Once a node is fully explored (all children done), we don’t revisit it again.

    recStack[] (recursion stack, sometimes called onPath)

        - Marks nodes that are part of the current DFS path.
        - When DFS goes deeper, nodes are pushed onto this “virtual stack” by setting recStack[node] = true.
        - When DFS backtracks, we pop by setting recStack[node] = false.

    Why do we need recStack?

        - Because a cycle means we revisit a node that’s still on our current DFS path.
        - If we just check visited[], we’d miss cycles:

    Imagine exploring node 0 → 1 → 2 → 0. By the time we revisit 0, visited[0] is already true. Without recStack, we’d wrongly say “safe”.

    recStack ensures we detect “back edges” (edges to an ancestor in the current DFS path), which is exactly what defines a cycle in DFS.
     */

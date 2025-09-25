package com.interview.Interview.Interview2025.Uber.Round2;


import java.util.ArrayList;
import java.util.PriorityQueue;

/*
You are in a city with n bus stops (labeled 1..n) and m bidirectional roads. Traversing a road u <-> v costs a
non-negative tax w. You also have k coupons. Each coupon can be used on one road to make its tax cost 0 for that
traversal. You may choose any k roads along your path (if available) to apply coupons; each road can consume at
most one coupon per traversal.

Return the minimum total tax required to travel from src to dst.
If no path exists, return -1

Function Signature
int minimumTax(int n, int[][] roads, int k, int src, int dst)

n: number of bus stops.
roads: m x 3 array where each row is [u, v, w] describing a bidirectional road with tax w.
k: number of coupons.
src, dst: start and destination bus stops.

Output
An integer: the minimum total tax from src to dst when using up to k coupons optimally.


Example 1

Input

n = 3, m = 2
roads = [[1,2,50],[2,3,25]]
k = 1
src = 1
dst = 3


Output

25


Explanation
Path: 1 -> 2 -> 3. Apply the single coupon on the road (1,2) (cost becomes 0), pay 25 on (2,3). Total = 0 + 25 = 25.
(If you instead coupon (2,3), total would be 50 + 0 = 50.)

Example 2

Input

n = 3, m = 2
roads = [[1,2,50],[2,3,25]]
k = 0
src = 1
dst = 3


Output

75


Explanation
No coupons: pay 50 + 25.

Example 3

Input

n = 3, m = 0
roads = []
k = 1
src = 1
dst = 3


Output

-1


Explanation
No roads → no path.

Hints

Think of state as (node, couponsUsed) and run Dijkstra on a layered graph of size n * (k+1).

From (u, c), for each edge (u, v, w):

Move to (v, c) with cost +w (don’t use a coupon),

If c < k, also move to (v, c+1) with cost +0 (use a coupon).

The answer is min_{c in [0..k]} dist[dst][c].

Expected Approach & Complexity

Use Dijkstra’s algorithm over n*(k+1) states with adjacency from roads.

Time: O((m * log(n*(k+1))) + m * k) ≈ O(m * log(nk)).

Space: O(n * (k+1) + m).

Edge Cases to Consider

src == dst (answer is 0).

Large weights; use 64-bit accumulation (long in Java).

Multiple edges between the same pair; self-loops.

k ≥ path length (you might zero out all edges on the path).
 */
public class MinTaxWithCoupons {

    // Edge in adjacency list
    static class Edge {
        int v;
        int w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    // PQ item: node, coupons used so far, weight so far, path so far
    static class Pair implements Comparable<Pair> {
        int node;
        int used;     // coupons used so far
        long wsf;     // weight so far (total tax)
        String psf;   // path so far as a string

        Pair(int node, int used, long wsf, String psf) {
            this.node = node;
            this.used = used;
            this.wsf = wsf;
            this.psf = psf;
        }

        public int compareTo(Pair other) {
            if (this.wsf < other.wsf) return -1;
            if (this.wsf > other.wsf) return 1;
            return 0;
        }
    }

    // Answer holder
    static class Answer {
        long minCost;
        String path;
        int couponsUsed;
        Answer(long minCost, String path, int couponsUsed) {
            this.minCost = minCost;
            this.path = path;
            this.couponsUsed = couponsUsed;
        }
    }

    int n; // number of bus stops (1-indexed)
    ArrayList<Edge>[] adj;

    @SuppressWarnings("unchecked")
    MinTaxWithCoupons(int n) {
        this.n = n;
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    // Undirected road (bidirectional) with tax w
    void addEdge(int u, int v, int w) {
        adj[u].add(new Edge(v, w));
        adj[v].add(new Edge(u, w));
    }

    // Dijkstra over (node, couponsUsed) states
    Answer dijkstraWithCoupons(int src, int dest, int K) {
        long INF = Long.MAX_VALUE / 4;

        // dist[node][used] = min cost to reach node having used 'used' coupons
        long[][] dist = new long[n + 1][K + 1];
        for (int i = 1; i <= n; i++) {
            for (int u = 0; u <= K; u++) {
                dist[i][u] = INF;
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        dist[src][0] = 0L;
        pq.add(new Pair(src, 0, 0L, String.valueOf(src)));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            // Skip if we already found a better way to this state
            if (cur.wsf != dist[cur.node][cur.used]) continue;

            // First time we pop dest is the optimal answer among all coupon usages
            if (cur.node == dest) {
                return new Answer(cur.wsf, cur.psf, cur.used);
            }

            // Relax neighbors
            ArrayList<Edge> list = adj[cur.node];
            for (int i = 0; i < list.size(); i++) {
                Edge e = list.get(i);

                // Option 1: Pay tax -> stay on same 'used' layer
                long costPay = cur.wsf + e.w;
                if (costPay < dist[e.v][cur.used]) {
                    dist[e.v][cur.used] = costPay;
                    pq.add(new Pair(e.v, cur.used, costPay, cur.psf + " -> " + e.v));
                }

                // Option 2: Use a coupon -> move to next 'used+1' layer, cost + 0
                if (cur.used < K) {
                    long costFree = cur.wsf;
                    if (costFree < dist[e.v][cur.used + 1]) {
                        dist[e.v][cur.used + 1] = costFree;
                        pq.add(new Pair(e.v, cur.used + 1, costFree, cur.psf + " -[coupon]-> " + e.v));
                    }
                }
            }
        }

        return new Answer(-1, "No path", 0);
    }

    // Demo with your sample
    public static void main(String[] args) {
        int n = 3;
        MinTaxWithCoupons g = new MinTaxWithCoupons(n);

        // Roads: (1-2:50), (2-3:25)
        g.addEdge(1, 2, 50);
        g.addEdge(2, 3, 25);

        int src = 1, dest = 3, k = 1;

        Answer ans = g.dijkstraWithCoupons(src, dest, k);

        System.out.println("Min tax = " + ans.minCost);
        System.out.println("Path = " + ans.path);
        System.out.println("Coupons used = " + ans.couponsUsed + " / " + k);
        // Expected: Min tax = 25 (use coupon on edge 1-2), Path = 1 -[coupon]-> 2 -> 3
    }
}


/*

Graph: 1 —50— 2 —25— 3, K = 1, src=1, dest=3
Node format: (stop, couponsUsed, costSoFar)
Edge labels: Pay w = pay tax w, Coupon = use one coupon (cost 0)

tiny decision:
(1, 0, 0)   start at stop 1 with 0 coupons used, cost 0
├── Pay 50
│   └── (2, 0, 50)
│       ├── Pay 25
│       │   └── (3, 0, 75)     path: 1 -> 2 -> 3
│       └── Coupon
│           └── (3, 1, 50)     path: 1 -> 2 -[coupon]-> 3
└── Coupon
    └── (2, 1, 0)
        └── Pay 25
            └── (3, 1, 25)     path: 1 -[coupon]-> 2 -> 3   ← BEST

What this shows

From (1,0,0) you can either Pay 50 to reach (2,0,50) or use Coupon to reach (2,1,0).
From (2,0,50) you still have the coupon:
Pay 25 → (3,0,75)
Coupon → (3,1,50)

From (2,1,0) you’re out of coupons, so only Pay 25 → (3,1,25).

Best outcome: (3,1,25) via 1 -[coupon]-> 2 -> 3, total tax 25 (use the coupon on the expensive 1–2 edge).
 */
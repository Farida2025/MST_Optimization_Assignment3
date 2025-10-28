/**
 * Implementations of Prim's and Kruskal's MST algorithms.
 *
 * Policies:
 *  - Prim: uses PriorityQueue (binary heap), adjacency list, visited set.
 *          Key operations counted: heap offers/polls, frontier expansions,
 *          visited checks (approximate, for empirical comparison).
 *  - Kruskal: sorts edges by weight and uses DSU to add non-cycling edges.
 *             Key operations counted: two find() per processed edge.
 *
 * Complexity:
 *  - Prim (binary heap):   O(E log V)
 *  - Kruskal (sort + DSU): O(E log E) ≈ O(E log V)
 */

package edu.aitu.daa.mst;

import java.util.*;

public class MSTAlgorithms {
    public static AlgorithmResult runPrim(Graph g) {
        long t0 = System.nanoTime();
        var cnt = new long[]{0};

        Map<String, List<Edge>> adj = g.buildAdjacency();
        Set<String> used = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        String start = g.nodes.get(0);
        used.add(start);
        for (Edge e : adj.getOrDefault(start, List.of())) { pq.offer(e); cnt[0]++; }

        while (mst.size() < g.nodes.size() - 1 && !pq.isEmpty()) {
            Edge e = pq.poll(); cnt[0]++;
            String a = e.from, b = e.to;
            String next = used.contains(a) ? b : a;
            if (used.contains(next)) continue;

            mst.add(e);
            used.add(next);
            for (Edge ne : adj.getOrDefault(next, List.of())) {
                String other = ne.from.equals(next) ? ne.to : ne.from;
                if (!used.contains(other)) { pq.offer(ne); cnt[0]++; }
            }
        }

        long t1 = System.nanoTime();
        int cost = mst.stream().mapToInt(ed -> ed.weight).sum();
        double ms = (t1 - t0) / 1_000_000.0;
        return AlgorithmResult.of(mst, cost, cnt[0], ms);
    }

    public static AlgorithmResult runKruskal(Graph g) {
        long t0 = System.nanoTime();
        var cnt = new long[]{0};

        List<Edge> edges = new ArrayList<>(g.edges);
        edges.sort(Comparator.comparingInt(e -> e.weight));

        DSU dsu = new DSU();
        dsu.makeSet(g.nodes);

        List<Edge> mst = new ArrayList<>();
        for (Edge e : edges) {
            cnt[0] += 2;
            if (!dsu.find(e.from).equals(dsu.find(e.to))) {
                if (dsu.union(e.from, e.to)) mst.add(e);
                if (mst.size() == g.nodes.size() - 1) break;
            }
        }

        long t1 = System.nanoTime();
        int cost = mst.stream().mapToInt(ed -> ed.weight).sum();
        double ms = (t1 - t0) / 1_000_000.0;
        return AlgorithmResult.of(mst, cost, cnt[0], ms);
    }
}

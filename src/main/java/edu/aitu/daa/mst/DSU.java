/**
 * Disjoint Set Union (Union-Find) with path compression and union by rank.
 *
 * API:
 *  - makeSet(nodes): initializes each node as its own parent
 *  - find(v): returns representative; flattens the tree (path compression)
 *  - union(a,b): merges two sets; returns true if merge happened
 *
 * Complexity:
 *  - Amortized almost O(1) per operation (inverse Ackermann).
 * Used by Kruskal's algorithm to detect cycles efficiently.
 */

package edu.aitu.daa.mst;
import java.util.HashMap;
import java.util.Map;

public class DSU {
    private final Map<String,String> parent = new HashMap<>();
    private final Map<String,Integer> rank = new HashMap<>();

    public void makeSet(Iterable<String> nodes) {
        for (String v : nodes) { parent.put(v, v); rank.put(v, 0); }
    }
    public String find(String v) {
        String p = parent.get(v);
        if (!p.equals(v)) parent.put(v, find(p));
        return parent.get(v);
    }
    public boolean union(String a, String b) {
        String ra = find(a), rb = find(b);
        if (ra.equals(rb)) return false;
        int rka = rank.get(ra), rkb = rank.get(rb);
        if (rka < rkb) parent.put(ra, rb);
        else if (rka > rkb) parent.put(rb, ra);
        else { parent.put(rb, ra); rank.put(ra, rka+1); }
        return true;
    }
}

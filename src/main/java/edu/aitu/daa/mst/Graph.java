/**
 * Graph model for a single dataset case.
 *
 * Fields:
 *  - id: graph identifier (used in reports)
 *  - nodes: list of vertex labels (e.g., ["A","B",...])
 *  - edges: list of input edges (only one direction is stored here)
 *
 * Methods:
 *  - buildAdjacency(): builds adjacency list; duplicates edges in the opposite
 *    direction to treat the graph as undirected.
 *
 * Usage:
 *  - Prim uses the adjacency list.
 *  - Kruskal uses the flat edge list directly.
 */

package edu.aitu.daa.mst;
import java.util.*;

public class Graph {
    public int id;
    public List<String> nodes;
    public List<Edge> edges;

    public Map<String, List<Edge>> buildAdjacency() {
        Map<String, List<Edge>> m = new HashMap<>();
        for (String v : nodes) m.put(v, new ArrayList<>());
        for (Edge e : edges) {
            m.get(e.from).add(e);
            m.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }
        return m;
    }
}

/**
 * Report bundle for a single input graph:
 *  - input stats (|V|, |E|)
 *  - Prim result
 *  - Kruskal result
 *
 * Invariants:
 *  - prim.total_cost == kruskal.total_cost (for connected graphs).
 * Used for JSON output and CSV summary generation.
 */

package edu.aitu.daa.mst;

public class GraphResult {
    public int graph_id;
    public InputStats input_stats;
    public AlgorithmResult prim;
    public AlgorithmResult kruskal;

    public static GraphResult from(Graph g, AlgorithmResult prim, AlgorithmResult kruskal) {
        var r = new GraphResult();
        r.graph_id = g.id;
        r.input_stats = new InputStats(g.nodes.size(), g.edges.size());
        r.prim = prim; r.kruskal = kruskal;
        return r;
    }
}

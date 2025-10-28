/**
 * Holds the result of a single MST algorithm run (Prim or Kruskal).
 *
 * Fields:
 *  - mst_edges: the list of edges included in the MST (size = |V|-1 when connected)
 *  - total_cost: sum of weights of mst_edges
 *  - operations_count: count of key algorithmic operations (see MSTAlgorithms for the policy)
 *  - execution_time_ms: wall-clock time in milliseconds for the run
 *
 * Notes:
 *  - This is a plain DTO used for JSON/CSV reporting.
 */

package edu.aitu.daa.mst;
import java.util.List;

public class AlgorithmResult {
    public List<Edge> mst_edges;
    public int total_cost;
    public long operations_count;
    public double execution_time_ms;

    public static AlgorithmResult of(List<Edge> edges, int cost, long ops, double ms) {
        var r = new AlgorithmResult();
        r.mst_edges = edges; r.total_cost = cost; r.operations_count = ops; r.execution_time_ms = ms;
        return r;
    }
}

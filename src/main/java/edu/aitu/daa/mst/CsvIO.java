/**
 * Writes a compact CSV summary for all graphs and both algorithms.
 *
 * Output columns (per graph):
 *  graph_id, vertices, edges,
 *  prim_cost, prim_ops, prim_time_ms,
 *  kruskal_cost, kruskal_ops, kruskal_time_ms
 *
 * Behavior:
 *  - Creates parent directories if needed.
 *  - Overwrites the target file.
 */

package edu.aitu.daa.mst;

import java.io.File;
import java.io.FileWriter;

public class CsvIO {
    public static void writeSummary(OutputData all, String path) throws Exception {
        new File(path).getParentFile().mkdirs();
        try (var w = new FileWriter(path)) {
            w.write("graph_id,vertices,edges,prim_cost,prim_ops,prim_time_ms,kruskal_cost,kruskal_ops,kruskal_time_ms\n");
            for (GraphResult r : all.results) {
                w.write(
                        r.graph_id + "," +
                                r.input_stats.vertices + "," +
                                r.input_stats.edges + "," +
                                r.prim.total_cost + "," +
                                r.prim.operations_count + "," +
                                r.prim.execution_time_ms + "," +
                                r.kruskal.total_cost + "," +
                                r.kruskal.operations_count + "," +
                                r.kruskal.execution_time_ms + "\n"
                );
            }
        }
    }
}

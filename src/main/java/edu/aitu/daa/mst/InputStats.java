/**
 * Simple holder for per-graph input statistics:
 *  - vertices: |V|
 *  - edges:    |E|
 *
 * Included into the JSON output to cross-check inputs vs results.
 */

package edu.aitu.daa.mst;
public class InputStats {
    public int vertices, edges;
    public InputStats() {}
    public InputStats(int v, int e) { this.vertices = v; this.edges = e; }
}

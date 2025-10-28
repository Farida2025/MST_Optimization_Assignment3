/**
 * Undirected graph edge (we store endpoints and weight).
 *
 * Fields:
 *  - from, to: endpoint labels
 *  - weight: non-negative cost
 *
 * Notes:
 *  - Default no-args ctor is required by Jackson.
 *  - Graph.buildAdjacency() mirrors edges to represent an undirected graph.
 */

package edu.aitu.daa.mst;

public class Edge {
    public String from;
    public String to;
    public int weight;

    public Edge() {}

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

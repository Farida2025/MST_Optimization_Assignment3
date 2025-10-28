/**
 * Root structure of the input JSON file:
 *
 * {
 *   "graphs": [ { "id":..., "nodes":[...], "edges":[...] }, ... ]
 * }
 *
 * Parsed by JsonIO.readInput(...).
 */

package edu.aitu.daa.mst;

import java.util.List;

public class InputData {
    public List<Graph> graphs;
}

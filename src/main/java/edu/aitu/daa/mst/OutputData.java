/**
 * Root structure of the output JSON:
 *
 * {
 *   "results": [ GraphResult, GraphResult, ... ]
 * }
 *
 * Also used by CsvIO to generate a tabular summary across all graphs.
 */

package edu.aitu.daa.mst;
import java.util.ArrayList;
import java.util.List;

public class OutputData {
    public List<GraphResult> results = new ArrayList<>();
}

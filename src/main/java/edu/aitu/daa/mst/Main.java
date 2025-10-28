/**
 * Entry point.
 *
 * CLI arguments:
 *  -in  <path-to-input-json>   (default: src/main/resources/datasets/ass_3_input.json)
 *  -out <path-to-output-json>  (default: out/ass_3_output.json)
 *  -csv <path-to-summary-csv>  (default: out/summary.csv)
 *
 * Flow:
 *  1) Read input graphs.
 *  2) For each graph, run Prim and Kruskal.
 *  3) Assert equal total_cost; collect results.
 *  4) Write JSON + CSV reports.
 */

package edu.aitu.daa.mst;

public class Main {
    public static void main(String[] args) throws Exception {

        String in  = "src/main/resources/datasets/ass_3_input.json";
        String out = "out/ass_3_output.json";
        String csv = "out/summary.csv";
        for (int i = 0; i + 1 < args.length; i++) {
            if (args[i].equals("-in"))  in  = args[++i];
            if (args[i].equals("-out")) out = args[++i];
            if (args[i].equals("-csv")) csv = args[++i];
        }

        InputData input = JsonIO.readInput(in);

        OutputData all = new OutputData();
        for (Graph g : input.graphs) {
            var prim   = MSTAlgorithms.runPrim(g);
            var krus   = MSTAlgorithms.runKruskal(g);
            if (prim.total_cost != krus.total_cost)
                throw new IllegalStateException("MST costs differ for graph " + g.id);

            all.results.add(GraphResult.from(g, prim, krus));
        }

        JsonIO.writeOutput(all, out);
        CsvIO.writeSummary(all, csv);
        System.out.println("Done → " + out + " & " + csv);
    }
}

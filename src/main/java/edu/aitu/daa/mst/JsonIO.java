/**
 * JSON I/O utilities (Jackson).
 *
 * Methods:
 *  - readInput(path): reads InputData from a JSON file
 *  - writeOutput(data, path): pretty-prints the OutputData JSON
 *
 * Behavior:
 *  - Ensures parent directories exist for output.
 *  - Overwrites the target file.
 */

package edu.aitu.daa.mst;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonIO {
    private static final ObjectMapper M = new ObjectMapper();

    public static InputData readInput(String path) throws Exception {
        return M.readValue(new File(path), InputData.class);
    }
    public static void writeOutput(OutputData data, String path) throws Exception {
        new File(path).getParentFile().mkdirs();
        M.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);
    }
}

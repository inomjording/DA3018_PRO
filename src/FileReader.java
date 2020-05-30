import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class FileReader {
    private int current = 0;
    private HashMap<String, Integer> mapping = new HashMap<>();

    private int allocateVertex(String vertex) {
        if (mapping.containsKey(vertex)) {
            return mapping.get(vertex);
        }
        int newIndex = current++;
        mapping.put(vertex, newIndex);
        return newIndex;
    }

    public Graph readFromFile(String filename) throws IOException {
        var graph = new Graph();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
        String line;
        int i = 1;

        while ((line = reader.readLine()) != null) {
            var p = line.split("\t");
            var first = allocateVertex(p[0]);
            var second = allocateVertex(p[1]);
            graph.addEdge(first, second);
           // if (i++ % 1000000 == 0)
           //     break;
        }
        reader.close();


        return graph;
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        var reader = new FileReader();
        var graph = reader.readFromFile("out/production/project/Spruce_fingerprint_2017-03-10_16.48.olp.m4");
        graph.degreeDistribution();
        System.out.println("Num components :" + graph.componentCount());
        System.out.println("Num vertices :" + graph.vertexCount());
        var elapsed = System.currentTimeMillis() - startTime;
        System.out.println("took: " + elapsed / 1000.0);
    }
}

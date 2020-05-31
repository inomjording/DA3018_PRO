import java.io.*;
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

    public void transformFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
        String line;
        int i = 1;

        var writer = new BufferedWriter(new FileWriter("converted.txt"));

        while ((line = reader.readLine()) != null) {
            var p = line.split("\t");
            var first = allocateVertex(p[0]);
            var second = allocateVertex(p[1]);
            writer.write(Integer.toString(first));
            writer.write(" ");
            writer.write(Integer.toString(second));
            writer.newLine();
            if (i++ % 100000 == 0)
                System.out.print(".");
        }
        reader.close();
        writer.close();
    }

    public Graph readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
        var graph = new Graph();
        String line;
        while ((line = reader.readLine()) != null) {
            var p = line.split(" ");
            int first = Integer.parseInt(p[0]);
            int second = Integer.parseInt(p[1]);
            graph.addEdge(first, second);
        }

        return graph;
    }

    public static void createNewFile(String[] args) throws IOException {
        var reader = new FileReader();
        reader.transformFile("out/production/project/Spruce_fingerprint_2017-03-10_16.48.olp.m4");
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        var reader = new FileReader();
        System.out.println("Reading file");
        var graph = reader.readFromFile("out/production/project/converted.txt");
        //System.out.println("Calc degree distr");
        //graph.degreeDistribution();
        System.out.println("Calc comp distr");
        var componentDistribution = graph.componentDistribution();
        System.out.println("Num components :" + componentDistribution.length);
        System.out.println("Num vertices :" + graph.vertexCount());
        var elapsed = System.currentTimeMillis() - startTime;
        var out = new PrintStream("components.txt");
        for (Integer integer : componentDistribution) {
            out.println(integer);
        }
        out.close();
        System.out.println("took: " + elapsed / 1000.0);
    }
}

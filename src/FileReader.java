import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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
        FileInputStream source = new FileInputStream(filename);
        var scanner = new Scanner(source);
        int i = 0;
       do {
            scanner.nextLine();
            var first = allocateVertex(scanner.next());
            var second = allocateVertex(scanner.next());
            graph.addEdge(first, second);
            if (i++ % 1000 == 0)
                System.out.print(". " + i + " .");
        } while (scanner.hasNextLine());
        source.close();

        return graph;
    }

    public static void main(String[] args) throws IOException {
        var reader = new FileReader();
        var graph = reader.readFromFile("out/production/project/Spruce_fingerprint_2017-03-10_16.48.olp.m4");
    }
}

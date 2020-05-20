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

    public Node readFromFile(String filename) throws IOException {
        FileInputStream source = new FileInputStream(filename);
        var scanner = new Scanner(source);
        while (!scanner.hasNextLine()) {
            var first = allocateVertex(scanner.next());
            var second = allocateVertex(scanner.next());
        }
        source.close();

        return null;
    }
}

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    LinkedList<Integer>[] adjList = new LinkedList[1023];

    public Graph() {
    }

    private LinkedList<Integer> getVertex(int vertex) {
        if (vertex >= adjList.length) { // stolen from ArrayList
            adjList = Arrays.copyOf(adjList, adjList.length +  (adjList.length >> 1));
        }

        var list = adjList[vertex];

        if (list == null) {
            list = new LinkedList<>();
            adjList[vertex] = list;
        }

        return list;
    }

    public void addEdge(int from, int to) {
        getVertex(from).addFirst(to);
        getVertex(to).addFirst(from);
    }

    public Integer[] distance(Integer start) {
        Integer[] distances = new Integer[adjList.length];
        Arrays.fill(distances, -1);
        distances[start] = 0;
        boolean[] visited = new boolean[adjList.length];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while (queue.size() != 0) {
            int v = queue.poll();
            for (var u : adjList[v]) {
                if (!visited[u])
                {
                    visited[u] = true;
                    queue.add(u);
                    distances[u] = distances[v] + 1;
                }
            }
        }

        return distances;
    }

    public int diameter() {
        int diameter = 0;
        for (int i = 0; i < adjList.length; i++) {
            Integer[] distances = distance(i);
            for (int j : distances) {
                if (j > diameter) {
                    diameter = j;
                }
            }
        }
        return diameter;
    }

    public void degreeDistribution() throws FileNotFoundException {
        var out = new PrintStream("distribution.txt");
        for (int i = 0; i < vertexCount(); i++) {
            out.println(i + " " + adjList[i].size());
        }
        out.close();
    }

    public int vertexCount() {
        for (int i = 0; i < adjList.length; i++) {
            if (adjList[i] == null) return i;
        }
        return adjList.length;
    }

    public int componentCount() {
        int numComponents = 0;
        int vertexCount = vertexCount();
        int start = 0;
        boolean[] visited = new boolean[vertexCount];
        do {
            markComponent(visited, start);
            numComponents++;
            start = findFirstFalse(visited);
        } while(start != -1);
        return numComponents;
    }

    private int findFirstFalse(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) return i;
        }
        return -1;
    }

    public void markComponent(boolean[] visited, int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while (queue.size() != 0) {
            int v = queue.poll();
            for (var u : adjList[v]) {
                if (!visited[u])
                {
                    visited[u] = true;
                    queue.add(u);
                }
            }
        }
    }
}

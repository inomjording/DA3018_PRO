import java.util.*;

public class Graph {
    ArrayList<LinkedList<Integer>> adjList = new ArrayList<>();

    public Graph() {
    }

    private LinkedList<Integer> getVertex(int vertex) {
        adjList.ensureCapacity(vertex + 1);
        var list = adjList.get(vertex);
        if (list == null) {
            list = new LinkedList<>();
            adjList.set(vertex, list);
        }

        return list;
    }

    public void addEdge(int from, int to) {
        getVertex(from).addFirst(to);
        getVertex(to).addFirst(from);
    }

    public Integer[] distance(Integer start) {
        Integer[] distances = new Integer[adjList.size()];
        Arrays.fill(distances, -1);
        distances[start] = 0;
        boolean[] visited = new boolean[adjList.size()];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while (queue.size() != 0) {
            int v = queue.poll();
            for (var u : adjList.get(v)) {
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
        for (int i = 0; i < adjList.size(); i++) {
            Integer[] distances = distance(i);
            for (int j : distances) {
                if (j > diameter) {
                    diameter = j;
                }
            }
        }
        return diameter;
    }

    public static void main() {
    }
}

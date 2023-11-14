package graph;


import java.util.*;

public class WeightedGraph<T> implements Graph<T> {
    public Map<T, List<Edge<T>>> adjacencyList;
    public int order;

    public WeightedGraph() {
        this.adjacencyList = new HashMap<>();
        this.order = 0;
    }

    // Time complexity: O(1)
    @Override
    public void addVertex(T x) {
        if (!adjacencyList.containsKey(x)) {
            adjacencyList.put(x, new ArrayList<>());
            order++;
        }
    }

    // Time complexity: O(V + E), where V is the number of vertices and E is the number of edges
    @Override
    public void removeVertex(T x) {
        if (adjacencyList.containsKey(x)) {
            adjacencyList.remove(x);
            order--;

            // Remove edges pointing to x
            adjacencyList.values().forEach(edges -> edges.removeIf(edge -> edge.destination.equals(x)));
        }
    }

    // Time complexity: O(1)
    @Override
    public boolean hasVertex(T v) {
        return adjacencyList.containsKey(v);
    }

    // Time complexity: O(1)
    @Override
    public List<T> getVertexes() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    // Time complexity: O(1)
    @Override
    public void addEdge(T v, T w) {
        addEdge(v, w, 1); // Default weight is 1
    }

    // Time complexity: O(1)
    @Override
    public void addEdge(T v, T w, int weight) {
        if (adjacencyList.containsKey(v) && adjacencyList.containsKey(w)) {
            adjacencyList.get(v).add(new Edge<>(w, weight));
            adjacencyList.get(w).add(new Edge<>(v, weight));
        }
    }

    // Time complexity: O(V + E), where V is the number of vertices and E is the number of edges
    @Override
    public void removeEdge(T v, T w) {
        if (adjacencyList.containsKey(v) && adjacencyList.containsKey(w)) {
            adjacencyList.get(v).removeIf(edge -> edge.destination.equals(w));
            adjacencyList.get(w).removeIf(edge -> edge.destination.equals(v));
        }
    }

    // Time complexity: O(1)
    @Override
    public boolean hasEdge(T v, T w) {
        if (adjacencyList.containsKey(v) && adjacencyList.containsKey(w)) {
            return adjacencyList.get(v).stream().anyMatch(edge -> edge.destination.equals(w)) ||
                    adjacencyList.get(w).stream().anyMatch(edge -> edge.destination.equals(v));
        }
        return false;
    }

    // Time complexity: O(V + E * log(V)), where V is the number of vertices and E is the number of edges
    @Override
    public int order() {
        return order;
    }

    // Time complexity: O(E), where E is the number of edges
    @Override
    public int alpha() {
        return adjacencyList.values().stream().mapToInt(List::size).sum() / 2;
    }

    // Time complexity: O(V + E * log(V)), where V is the number of vertices and E is the number of edges
    @Override
    public List<T> getAdjacencyList(T v) {
        List<T> adjacency = new ArrayList<>();
        if (adjacencyList.containsKey(v)) {
            for (Edge<T> edge : adjacencyList.get(v)) {
                adjacency.add(edge.destination);
            }
        }
        return adjacency;
    }

    // Time complexity: O(E * log(V)), where E is the number of edges and V is the number of vertices
    public List<Edge<T>> prim() {
        List<Edge<T>> minimumSpanningTree = new ArrayList<>();
        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>();

        T startVertex = getVertexes().get(0);
        priorityQueue.addAll(adjacencyList.get(startVertex));

        List<T> visited = new ArrayList<>();
        visited.add(startVertex);

        while (!priorityQueue.isEmpty()) {
            Edge<T> currentEdge = priorityQueue.poll();
            if (!visited.contains(currentEdge.destination)) {
                visited.add(currentEdge.destination);
                minimumSpanningTree.add(currentEdge);
                priorityQueue.addAll(adjacencyList.get(currentEdge.destination));
            }
        }

        return minimumSpanningTree;
    }
}

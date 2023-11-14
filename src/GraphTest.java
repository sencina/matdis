
import graph.Edge;
import graph.WeightedGraph;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;


public class GraphTest {
    @Test
    public void testAddVertex() {
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        assertTrue(graph.hasVertex(1));
        assertTrue(graph.hasVertex(2));
        assertFalse(graph.hasVertex(3));
    }

    @Test
    public void testRemoveVertex() {
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        graph.removeVertex(1);
        assertFalse(graph.hasVertex(1));
        assertFalse(graph.hasEdge(1, 2));
    }

    @Test
    public void testAddEdge() {
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 3);

        assertTrue(graph.hasEdge(1, 2));
        assertTrue(graph.hasEdge(2, 1));

        // Additional assertions to check if the edge weight is correct
        List<Edge<Integer>> edgesFrom1 = graph.adjacencyList.get(1);
        List<Edge<Integer>> edgesFrom2 = graph.adjacencyList.get(2);

        assertEquals(1, edgesFrom1.size());
        assertEquals(1, edgesFrom2.size());

        assertEquals(3, edgesFrom1.get(0).weight);
        assertEquals(3, edgesFrom2.get(0).weight);
    }

    @Test
    public void testRemoveEdge() {
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 3);
        graph.removeEdge(1, 2);
        assertFalse(graph.hasEdge(1, 2));
    }

    @Test
    public void testPrim() {
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 1, 3);

        assertEquals(2, graph.prim().size());
    }

}


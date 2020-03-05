package algs.graph;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {

    @Test
    void shouldCreateGraphWithoutEdge() {
        Graph graph = new Graph(5);
        assertEquals(5, graph.numberOfVertices());
        assertEquals(0, graph.numberOfEdges());
        range(0,5).forEach(vertex -> assertTrue(graph.adjacent(vertex).isEmpty()));
    }

    @Test
    void shouldAddEdgeToGraph() {
        Graph graph = new Graph(7);
        graph.addEdge(0, 5);
        verifyEdge(graph, 0, 5, 2);
        graph.addEdge(4,3);
        verifyEdge(graph, 4, 3, 4);
        graph.addEdge(0,1);
        verifyEdge(graph, 0, 1, 6);
        graph.addEdge(6,4);
        verifyEdge(graph, 6, 4, 8);
        graph.addEdge(5,4);
        verifyEdge(graph, 5, 4, 10);
        graph.addEdge(0,2);
        verifyEdge(graph, 0, 2, 12);
    }

    @Test
    void shouldCreateGraphFromFile() throws URISyntaxException, IOException {
        Graph graph = new Graph( File.separator + "tinyG.txt");
        URI uri = getClass().getResource( "/tinyGraph.txt").toURI();
        String expected = new String(Files.readAllBytes(Paths.get(uri)));
        assertEquals(expected, graph.toString());
    }

    private void verifyEdge(Graph graph, int vertex1, int vertex2, int numberOfEdge) {
        assertTrue(graph.adjacent(vertex1).contains(vertex2));
        assertTrue(graph.adjacent(vertex2).contains(vertex1));
        assertEquals(numberOfEdge, graph.numberOfEdges());
    }
}
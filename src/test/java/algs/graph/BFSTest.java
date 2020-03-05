package algs.graph;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Iterator;

import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    void shouldCheckPathExistBetweenTwoVertex() {
        Graph graph = new Graph(File.separator + "tinyG.txt");
        BFS bfs = new BFS(graph, 0);
        rangeClosed(1, 6).forEach(vertex ->
                assertTrue(bfs.hasPathTo(vertex)));
    }

    @Test
    void shouldGetPathToVertex() {
        Graph graph = new Graph(File.separator + "tinyG.txt");
        BFS bfs = new BFS(graph, 0);
        Iterator<Integer> iterator = bfs.pathTo(4).iterator();
        assertEquals(4, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(0, iterator.next().intValue());
    }
}
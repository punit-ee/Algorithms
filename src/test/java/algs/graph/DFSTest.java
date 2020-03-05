package algs.graph;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Iterator;

import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.*;

class DFSTest {

    @Test
    void shouldCheckPathExistBetweenTwoVertex() {
        Graph graph = new Graph(File.separator + "tinyG.txt");
        DFS dfs = new DFS(graph, 0);
        rangeClosed(1, 6).forEach(vertex ->
                assertTrue(dfs.hasPathTo(vertex)));
    }

    @Test
    void shouldGetPathToVertex() {
        Graph graph = new Graph(File.separator + "tinyG.txt");
        DFS dfs = new DFS(graph, 0);
        Iterator<Integer> iterator = dfs.pathTo(6).iterator();
        assertEquals(6, iterator.next().intValue());
        assertEquals(4, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(0, iterator.next().intValue());
    }
}
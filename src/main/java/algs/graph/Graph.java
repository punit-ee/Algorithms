package algs.graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

public class Graph {

    private List<Integer>[] adjacent;

    public Graph(int noOfVertex) {
        this.adjacent = new List[noOfVertex];
        range(0, noOfVertex).forEach(index -> this.adjacent[index] = new ArrayList<>());
    }

    public Graph(String fileName) {
        String[] lines = readFile(fileName);
        int noOfVertex = Integer.valueOf(lines[0]);
        if (noOfVertex <= 0) {
            throw new IllegalArgumentException("no of vertex should be grater than 1");
        }
        this.adjacent = new List[noOfVertex];
        range(0, noOfVertex).forEach(index -> this.adjacent[index] = new ArrayList<>());
        rangeClosed(1, noOfVertex)
                .mapToObj(index -> lines[index].split(" "))
                .filter(edges -> edges.length >= 2)
                .forEach(edges -> addEdge(valueOf(edges[0]), valueOf(edges[1])));
    }

    private String[] readFile(String fileName) {
        try {
            Path path = Paths.get(getClass().getResource(fileName).toURI());
            String file = new String(Files.readAllBytes(path));
            return file.split("\n");
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int numberOfVertices() {
        return adjacent.length;
    }

    public int numberOfEdges() {
        return Arrays.stream(adjacent)
                .mapToInt(List::size)
                .sum();
    }

    public List<Integer> adjacent(int vertex) {
        return adjacent[vertex];
    }

    public void addEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        adjacent[vertex1].add(vertex2);
        adjacent[vertex2].add(vertex1);
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= adjacent.length) {
            throw new IllegalArgumentException("Vertex is not between 0 to " + (adjacent.length - 1));
        }
    }

    @Override
    public String toString() {
        return range(0, adjacent.length)
                .mapToObj(index -> index + "->" + adjacent[index].stream()
                        .map(String::valueOf)
                        .collect(joining(",")))
                .collect(joining("\n"));
    }
}
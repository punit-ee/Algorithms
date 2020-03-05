package algs.graph;

import java.util.Stack;

import static java.util.Collections.emptyList;

public class DFS {
    private final int source;
    private boolean[] marked;
    private int[] edgeTo;

    public DFS(Graph graph, int source) {
        this.source = source;
        int numberOfVertices = graph.numberOfVertices();
        marked = new boolean[numberOfVertices];
        edgeTo = new int[numberOfVertices];
        dfs(graph, source);
    }

    private void dfs(Graph graph, int source) {
        marked[source] = true;
        graph.adjacent(source).stream()
             .filter(vertex -> !marked[vertex])
             .forEach(vertex -> {
                 dfs(graph, vertex);
                 edgeTo[vertex] = source;
             });
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return emptyList();
        }
        Stack<Integer> path = new Stack<>();
        for (int i = vertex; i != source; i=edgeTo[i] ) {
            path.push(i);
        }
        path.push(source);
        return path;
    }
}

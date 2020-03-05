package algs.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    private boolean[] marked;
    private int[] edgesTo;
    private int source;

    public BFS(Graph graph, int source) {
        marked = new boolean[graph.numberOfVertices()];
        edgesTo = new int[graph.numberOfVertices()];
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(Graph graph, int source) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        marked[source] = true;
        while (!queue.isEmpty()) {
            Integer element = queue.poll();
            graph.adjacent(element).forEach(vertex -> {
                if (!marked[vertex]) {
                    queue.offer(vertex);
                    marked[vertex] = true;
                    edgesTo[vertex] = element;
                }
            });
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        Stack<Integer> stack = new Stack<>();
        for (int i = vertex; i != source; i = edgesTo[i]) {
            stack.push(i);
        }
        stack.push(source);
        return stack;
    }
}

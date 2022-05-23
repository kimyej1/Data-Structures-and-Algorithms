package HW13;

import java.util.*;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (graph == null) {
            throw new IllegalArgumentException("Null graph.");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("No start vertex");
        }
        List<Vertex<T>> list = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        queue.add(start);
        list.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> curr = queue.peek();
            if (curr == null) {
                throw new IllegalArgumentException("Null vertex");
            }
            List<VertexDistance<T>> adj = adjList.get(curr);
            for (VertexDistance<T> vd : adj) {
                if (!list.contains(vd.getVertex())) {
                    queue.add(vd.getVertex());
                    list.add(vd.getVertex());
                }
            }
            queue.remove();
        }
        return list;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (graph == null) {
            throw new IllegalArgumentException("Null graph.");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("No start vertex");
        }
        List<Vertex<T>> list = new ArrayList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        Set<Vertex<T>> vlist = new HashSet<>();
        dfsHelp(start, vlist, list, adjList);
        return list;
    }
    private static <T> void dfsHelp(Vertex<T> curr,
                                    Set<Vertex<T>> vlist,
                                    List<Vertex<T>> list,
                                    Map<Vertex<T>,
                                            List<VertexDistance<T>>> adjList) {
        list.add(curr);
        vlist.add(curr);
        for (VertexDistance<T> v: adjList.get(curr)) {
            if (!vlist.contains(v.getVertex())) {
                dfsHelp(v.getVertex(), vlist, list, adjList);
            }
        }
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (start == null) {
            throw new IllegalArgumentException("Null start vertex");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Null graph");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex not in graph");
        }
        Set<Edge<T>> returnSet = new HashSet<>();
        Queue<Edge<T>> queue = new PriorityQueue<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, List<VertexDistance<T>>> vMap = graph.getAdjList();

        for (VertexDistance<T> v: vMap.get(start)) {
            queue.add(new Edge<>(start, v.getVertex(), v.getDistance()));
        }

        visitedSet.add(start);

        while (!queue.isEmpty()
                && !(visitedSet.size() == graph.getVertices().size())) {
            Edge<T> currEdge = queue.remove();
            if (!visitedSet.contains(currEdge.getV())) {
                visitedSet.add(currEdge.getV());
                returnSet.add(new Edge<>(currEdge.getU(), currEdge.getV(),
                        currEdge.getWeight()));
                returnSet.add(new Edge<>(currEdge.getV(), currEdge.getU(),
                        currEdge.getWeight()));

                for (VertexDistance<T> v: vMap.get(currEdge.getV())) {
                    if (!visitedSet.contains(v.getVertex())) {
                        queue.add(new Edge<>(currEdge.getV(), v.getVertex(),
                                v.getDistance()));
                    }
                }
            }
        }

        if ((returnSet.size() / 2) == graph.getVertices().size() - 1) {
            //checks edge case of disconnected graph
            return returnSet;
        } else {
            return null;
        }
    }

}
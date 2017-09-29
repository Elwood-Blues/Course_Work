package graph;

import java.util.*;

/**
 * This abstract class represents directed and undirected graphs and
 * provides graph searching operations for them.
 * Subclasses should call addVertices, addEdges, and setDirected to initialize
 * the graph.
 * Graphs are internally represented using adjacency lists.
 * @author tcolburn
 */
public abstract class Graph {

    /**
     * Adds vertices to this graph.
     * First clears the hash map of adjacency lists then adds a new
     * empty adjacency list to the map for each vertex.
     * @param vertices the list of vertices to be added
     */
    public void addVertices(List<Vertex> vertices) {
        adj_hash.clear();
        Iterator<Vertex> vertexIterator = vertices.iterator();
        while ( vertexIterator.hasNext() ) {
            Vertex v = (Vertex) vertexIterator.next();
            adj_hash.put(v, new LinkedList<Vertex>());
        }
    }
    
    /**
     * Adds edges to this graph by adding to the adjacency lists.
     * If the graph is undirected, two adjacency list elements are
     * added for each edge.
     * If the graph is directed, one is added.
     * @param edges the list of edges to be added
     */
    public void addEdges(List<Edge> edges) {
        Iterator<Edge> iter = edges.iterator();
        while (iter.hasNext()) {
            Edge e = iter.next();
            addEdge(e.getV1(), e.getV2());
        }
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }
    /**
     * Adds an edge (v1, v2) to this graph.
     * If the graph is undirected, (v2, v1) is also added.
     * @param v1 The first vertex in the edge
     * @param v2 The second vertex in the edge
     */
    private void addEdge(Vertex v1, Vertex v2) {
        adj_hash.get(v1).add(v2);
        if (!directed) {
            adj_hash.get(v2).add(v1);
        }
    }

    /**
     * Tests whether a pair of vertices forms an edge in this graph.
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @return Whether there is an edge from the first vertex to the second
     */
    public boolean isEdge(Vertex vertex1, Vertex vertex2) {
        return adj_hash.get(vertex1).contains(vertex2);
    }

    /**
     * Performs a breadth-first search of this graph from a given starting vertex.
     * For each vertex that is reachable from the start, this operation computes
     * its distance from the start and its predecessor on the search path.
     * @param start The start vertex
     */
    public void breadthFirstSearch(Vertex start) {

        DequeAdder tailAdder = new DequeAdder() {
            public void add(Vertex vertex, Deque<Vertex> deque) {
                deque.addLast(vertex);
            }
        };

        search(start, tailAdder);
    }

    /**
     * Performs a depth-first search of this graph from a given starting vertex.
     * For each vertex that is reachable from the start, this operation computes
     * its distance from the start and its predecessor on the search path.
     * @param start The start vertex
     */
    public void depthFirstSearch(Vertex start) {

        DequeAdder headAdder = new DequeAdder() {
            public void add(Vertex vertex, Deque<Vertex> deque) {
                deque.addFirst(vertex);
            }
        };

        search(start, headAdder);
    }

    /**
     * Produces a string representation of the search path from a start
     * vertex to an end vertex in this graph.
     * This method is intended to be called after subjecting the graph to
     * a search.
     * The string representation for an individual vertex is obtained
     * using its <b>toString</b> method.
     * @param start The start vertex in the search path
     * @param end The end vertex in the search path
     * @return A string representation of the search path
     */
    public String pathString(Vertex start, Vertex end) {
        if ( start == end ) {
            return vertexString(start);
        }
        else if ( end.getPredecessor() == null ) {
            return "";
        }
        else {
            return pathString(start, end.getPredecessor()) + vertexString(end);
        }
    }

    /**
     * A string representation of a vertex with end-of-lines before and after.
     * @param v A vertex
     * @return The vertex's string representation
     */
    private String vertexString(Vertex v) {
        return "\n" + v.toString() + "\n";
    }

    /**
     * The core search operation for this graph.
     * It uses a double-ended queue so that either breadth-first or
     * depth-first search can be performed depending on to which end of the
     * queue newly discovered vertices are added.
     * @param start The start vertex for the search
     * @param adder A purely functional object that adds to either the head or
     * tail of a double-ended queue
     */
    private void search(Vertex start, DequeAdder adder) {
        searchInit();
        start.setOpen(false);
        start.setDistance(0);
        Deque<Vertex> deque = new LinkedList<Vertex>();
        deque.add(start);
        while ( !deque.isEmpty() ) {
            Vertex v = deque.remove();
            List<Vertex> adjList = adj_hash.get(v);
            if ( adjList != null ) {
                Iterator iter = adjList.iterator();
                while ( iter.hasNext() ) {
                    Vertex successor = (Vertex) iter.next();
                    if ( successor.isOpen() ) {
                        successor.setOpen(false);
                        successor.setDistance(v.getDistance()+1);
                        successor.setPredecessor(v);
                        adder.add(successor, deque);
                    }
                }
            }
        }
    }

    /**
     * An initializing operation for the core search operation.
     * All vertices are set to open with null predecessors and
     * a distance of inifinity from the start.
     */
    private void searchInit() {
        Iterator<Vertex> iterator = adj_hash.keySet().iterator();
        while ( iterator.hasNext() ) {
            Vertex vertex = (Vertex) iterator.next();
            vertex.setOpen(true);
            vertex.setDistance(INFINITY);
            vertex.setPredecessor(null);
        }
    }

    /**
     * Creates a string representation of this graph's adjacency list for testing.
     * @return The string representation of the adjacency list
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Iterator vertexIterator = adj_hash.keySet().iterator();
        while ( vertexIterator.hasNext() ) {
            Vertex vertex = (Vertex) vertexIterator.next();
            builder.append("\n" + vertex.getName());
            List<Vertex> adjList = adj_hash.get(vertex);
            if ( adjList == null ) continue;
            Iterator listIterator = adjList.iterator();
            while ( listIterator.hasNext() ) {
                Vertex adjVertex = (Vertex) listIterator.next();
                builder.append(" -> " + adjVertex.getName());
            }
        }
        return builder.toString();
    }

    public List<Vertex> getAdjacencyList(Vertex v) {
        return adj_hash.get(v);
    }
    
    /**
     * Whether this graph is directed or not.
     */
    private boolean directed = false;

    /**
     * The vertex's adjacency lists are stored in a hash table whose
     * keys are vertices and whose values are lists of vertices.
     */
    private HashMap<Vertex, List<Vertex>> adj_hash = new HashMap<Vertex, List<Vertex>>();

    /**
     * A large number used to represent infinity for the search operations.
     * It is public just so test classes can use it.
     */
    public static final int INFINITY = 999999999;

}

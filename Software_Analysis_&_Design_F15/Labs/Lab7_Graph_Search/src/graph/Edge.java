/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

/**
 * A simple class to represent a pair of vertices in a graph.
 * @author tcolburn
 */
public class Edge {

    /**
     * Constructs an edge object out of two vertices
     * @param v1 The first vertex in the edge
     * @param v2 The second vertex in the edge
     */
    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Accessor for the first vertex of this edge.
     * @return The first vertex
     */
    public Vertex getV1() {
        return v1;
    }

    /**
     * Accessor for the second vertex of this edge.
     * @return The second vertex
     */
    public Vertex getV2() {
        return v2;
    }
    
    /**
     * The first vertex of this edge.
     */
    private Vertex v1;

    /**
     * The second vertex of this edge.
     */
    private Vertex v2;
}

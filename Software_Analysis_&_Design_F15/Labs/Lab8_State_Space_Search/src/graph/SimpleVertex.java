package graph;

import framework.Move;

/**
 * A simple implementation of the Vertex interface.
 * @author tcolburn
 */
public class SimpleVertex implements Vertex {

    public SimpleVertex() {
        this("");
    }

    public SimpleVertex(String name) {
        this.name = name;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public int getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public boolean isOpen() {
        return open;
    }

    /**
     * The string representation for a simple vertex is simply its name.
     * @return The vertex name
     */
    public String toString() {
        return name;
    }

    /**
     * The move that resulted in this vertex.  If null, this is the
     * initial vertex.
     */
    private Move move;

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    /**
     * The name of this vertex
     */
    private String name;

    /**
     * The distance of this vertex from a start vertex in a graph search.
     */
    private int distance;

    /**
     * The predecessor vertex of this vertex in a graph search.
     */
    private Vertex predecessor;

    /**
     * Whether this vertex is still open for the purposes of a graph search.
     */
    private boolean open;

}

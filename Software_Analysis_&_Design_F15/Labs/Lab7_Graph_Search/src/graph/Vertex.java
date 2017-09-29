/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

/**
 * This interface represents a vertex in a graph.
 * A vertex must have a name, and it must represent search information,
 * including whether the vertex is open, its distance from a start vertex,
 * and its predecessor in the search.
 * It must also provide a <b>toString</b> method for printing purposes.
 * @author tcolburn
 */
public interface Vertex {

    /**
     * Accessor for this vertex's name.
     * @return A name this vertex
     */
    String getName();
    
    /**
     * Mutator for this vertex's name.
     * @param A name this vertex
     */
    void setName(String name);
    
    /**
     * Accessor for this vertex's distance from the start vertex in a search.
     * @return Distance from the start
     */
    int getDistance();

    /**
     * Mutator for this vertex's distance from the start vertex in a search.
     * @param distance Distance from the start
     */
    void setDistance(int distance);

    /**
     * Accessor for this vertex's predecessor in the search path.
     * @return Predecessor vertex
     */
    Vertex getPredecessor();

    /**
     * Mutator for this vertex's predecessor in the search path.
     * @param pred Predecessor vertex
     */
    void setPredecessor(Vertex pred);

    /**
     * Returns whether this vertex is still open for the purposes
     * of graph search
     * @return Whether the vertex is open
     */
    boolean isOpen();

    /**
     * Mutator for whether this vertex is still open for the purposes
     * of graph search
     * @param open Whether the vertex is open
     */
    void setOpen(boolean open);

    /**
     * Returns a printed representation of this vertex as a string.
     * @return A printed representation
     */
    String toString();

}

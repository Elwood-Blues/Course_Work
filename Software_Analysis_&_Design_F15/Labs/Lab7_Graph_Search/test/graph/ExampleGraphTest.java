package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the ExampleGraph class.
 * @author tcolburn
 */
public class ExampleGraphTest {

    private ExampleGraph graph;
    private Vertex r, s, t, u, v, w, x, y;

    /**
     * Creates an ExampleGraph object and gets the vertices.
     */
    public ExampleGraphTest() {

        graph = new ExampleGraph();

        r = graph.getR();
        s = graph.getS();
        t = graph.getT();
        u = graph.getU();
        v = graph.getV();
        w = graph.getW();
        x = graph.getX();
        y = graph.getY();
    }
    
    /**
     * Tests the adjacency list representation of the graph.
     */
    @Test
    public void testAdjacencyList() {
        assertTrue(graph.getAdjacencyList(r).size()==2);
        assertTrue(graph.getAdjacencyList(s).size()==2);
        assertTrue(graph.getAdjacencyList(t).size()==3);
        assertTrue(graph.getAdjacencyList(u).size()==2);
        assertTrue(graph.getAdjacencyList(v).size()==1);
        assertTrue(graph.getAdjacencyList(w).size()==3);
        assertTrue(graph.getAdjacencyList(x).size()==3);
        assertTrue(graph.getAdjacencyList(y).size()==2);
        System.out.println("Adjacency Lists:\n" + graph.toString() + "\n");
    }
    
    /**
     * Tests the BFS algorithm on the graph.
     */
    @Test
    public void testBreadthFirstSearch() {

        graph.breadthFirstSearch(s);

        assertTrue(y.getDistance() == 3);
        System.out.print("The breadth-first path from " +s+ " to " +y+ ":\n");
        System.out.println(graph.pathString(s, y));
    }

    /**
     * Tests the DFS algorithm on the graph.
     */
    @Test
    public void testDepthFirstSearch() {

        graph.depthFirstSearch(s);
        
        assertTrue(y.getDistance() == 4);
        System.out.print("The depth-first path from " +s+ " to " +y+ ":\n");
        System.out.println(graph.pathString(s, y));
    }
}
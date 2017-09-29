package farmer;

import graph.Vertex;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the FarmerGraph class.
 * @author tcolburn
 */
public class FarmerGraphTest {
    
    private FarmerGraph graph;

    private Vertex v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;

    /**
     * Creates a FarmerGraph object and gets the vertices.
     */
    public FarmerGraphTest() {

        graph = new FarmerGraph();
        
        v1 = graph.getV1();
        v2 = graph.getV2();
        v3 = graph.getV3();
        v4 = graph.getV4();
        v5 = graph.getV5();
        v6 = graph.getV6();
        v7 = graph.getV7();
        v8 = graph.getV8();
        v9 = graph.getV9();
        v10 = graph.getV10();
    }
    
    /**
     * Tests the adjacency list representation of the graph.
     */
    @Test
    public void testAdjacencyList() {
        assertTrue(graph.getAdjacencyList(v1).size()==1);
        assertTrue(graph.getAdjacencyList(v2).size()==2);
        assertTrue(graph.getAdjacencyList(v3).size()==2);
        assertTrue(graph.getAdjacencyList(v4).size()==3);
        assertTrue(graph.getAdjacencyList(v5).size()==2);
        assertTrue(graph.getAdjacencyList(v6).size()==1);
        assertTrue(graph.getAdjacencyList(v7).size()==2);
        assertTrue(graph.getAdjacencyList(v8).size()==2);
        assertTrue(graph.getAdjacencyList(v9).size()==3);
        assertTrue(graph.getAdjacencyList(v10).size()==2);
        System.out.println("Adjacency Lists:\n" + graph.toString() + "\n");
    }
    
    /**
     * Tests the BFS algorithm on the graph.
     */
    @Test
    public void testBreadthFirstSearch() {
        graph.breadthFirstSearch(v1);
        
        assertTrue(v6.getDistance() == 7);
        System.out.print("The breadth-first solution:\n");
        System.out.println(graph.pathString(v1, v2));
    }
    
    /**
     * Tests the DFS algorithm on the graph.
     */
    @Test
    public void testDepthFirstSearch() {
        graph.depthFirstSearch(v1);
        
        assertTrue(v6.getDistance() == 7);
        System.out.print("The depth-first solution:\n");
        System.out.println(graph.pathString(v1, v2));
    }
}
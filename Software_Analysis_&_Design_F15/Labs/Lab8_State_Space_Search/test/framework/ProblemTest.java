package framework;

import farmer.FarmerState;
import farmer.FarmerProblem;
import graph.Vertex;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the search and expand operations for state space search.
 * @author tcolburn
 */
public class ProblemTest {
    
    Vertex v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;
    
    Vertex v1Copy, v4Copy, v7Copy, v10Copy;
    
    Problem problem;
    
    /**
     * Creates vertices containing all possible states of the FWGC problem
     * as shown in the figure at:
     * 
     *    http://www.d.umn.edu/~tcolburn/cs2511/slides/graphs/images/farmer-states-4.png
     */
    public ProblemTest() {
        
        v1 = new FarmerState(FarmerState.Side.WEST,  // F |  |
                             FarmerState.Side.WEST,  // W |  |
                             FarmerState.Side.WEST,  // G |  |
                             FarmerState.Side.WEST); // C |  |
    
        v2 = new FarmerState(FarmerState.Side.WEST,  // F |  |
                             FarmerState.Side.EAST,  //   |  | W
                             FarmerState.Side.WEST,  // G |  |
                             FarmerState.Side.EAST); //   |  | C
    
        v3 = new FarmerState(FarmerState.Side.WEST,  // F |  |
                             FarmerState.Side.EAST,  //   |  | W
                             FarmerState.Side.WEST,  // G |  |
                             FarmerState.Side.WEST); // C |  |  
    
        v4 = new FarmerState(FarmerState.Side.WEST,  // F |  |  
                             FarmerState.Side.WEST,  // W |  |
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.WEST); // C |  |
    
        v5 = new FarmerState(FarmerState.Side.WEST,  // F |  |
                             FarmerState.Side.WEST,  // W |  |  
                             FarmerState.Side.WEST,  // G |  |
                             FarmerState.Side.EAST); //   |  | C
    
        v6 = new FarmerState(FarmerState.Side.EAST,  //   |  | F
                             FarmerState.Side.EAST,  //   |  | W
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.EAST); //   |  | C
    
        v7 = new FarmerState(FarmerState.Side.EAST,  //   |  | F
                             FarmerState.Side.WEST,  // W |  |
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.WEST); // C |  |
    
        v8 = new FarmerState(FarmerState.Side.EAST,  //   |  | F
                             FarmerState.Side.WEST,  // W |  |  
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.EAST); //   |  | C
    
        v9 = new FarmerState(FarmerState.Side.EAST,  //   |  | F
                             FarmerState.Side.EAST,  //   |  | W
                             FarmerState.Side.WEST,  // G |  |  
                             FarmerState.Side.EAST); //   |  | C
    
        v10= new FarmerState(FarmerState.Side.EAST,  //   |  | F
                             FarmerState.Side.EAST,  //   |  | W
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.WEST); // C |  |
        

        
        v1Copy = 
             new FarmerState(FarmerState.Side.WEST,  // F |  |
                             FarmerState.Side.WEST,  // W |  |
                             FarmerState.Side.WEST,  // G |  |
                             FarmerState.Side.WEST); // C |  |    
    
        v4Copy = 
             new FarmerState(FarmerState.Side.WEST,  // F |  |  
                             FarmerState.Side.WEST,  // W |  |
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.WEST); // C |  | 
    
        v7Copy = 
             new FarmerState(FarmerState.Side.EAST,  //   |  | F
                             FarmerState.Side.WEST,  // W |  |
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.WEST); // C |  | 
    
        v10Copy= 
             new FarmerState(FarmerState.Side.EAST,  //   |  | F
                             FarmerState.Side.EAST,  //   |  | W
                             FarmerState.Side.EAST,  //   |  | G
                             FarmerState.Side.WEST); // C |  |    
        
        problem = new FarmerProblem();
    }
    
    /**
     * This tests the Problem class's occursOnPath method.
     * It first links the vertices such that:
     * 
     *    v1 - v7 - v4 - v10 - v3 - v9 - v2 - v6
     * 
     * This represents the upper solution of the problem as shown in the
     * figure at:
     * 
     *    http://www.d.umn.edu/~tcolburn/cs2511/slides/graphs/images/farmer-states-5.png
     * 
     * It then makes assertions about which vertices lie on various solution paths.
     */
    @Test
    public void testOccursOnPath() {
        v1.setPredecessor(null);
        v7.setPredecessor(v1);
        v4.setPredecessor(v7);
        v10.setPredecessor(v4);
        v3.setPredecessor(v10);
        v9.setPredecessor(v3);
        v2.setPredecessor(v9);
        v6.setPredecessor(v2);
        
        // v1, v7, v4, and v10 are on the path to v10
        assertTrue(problem.occursOnPath(v1, v10));
        assertTrue(problem.occursOnPath(v7, v10));
        assertTrue(problem.occursOnPath(v4, v10));
        assertTrue(problem.occursOnPath(v10, v10));
        
        // the rest of the vertices are not on the path to v10
        assertFalse(problem.occursOnPath(v3, v10));
        assertFalse(problem.occursOnPath(v9, v10));
        assertFalse(problem.occursOnPath(v2, v10));
        assertFalse(problem.occursOnPath(v6, v10));
        assertFalse(problem.occursOnPath(v8, v10));
        assertFalse(problem.occursOnPath(v5, v10));
        
        // v8 and v5 are not on the path to v6
        assertFalse(problem.occursOnPath(v8, v6));
        assertFalse(problem.occursOnPath(v5, v6));
        
        // the rest of the vertices are on the path to v6
        assertTrue(problem.occursOnPath(v1, v6));
        assertTrue(problem.occursOnPath(v7, v6));
        assertTrue(problem.occursOnPath(v4, v6));
        assertTrue(problem.occursOnPath(v10, v6));
        assertTrue(problem.occursOnPath(v3, v6));
        assertTrue(problem.occursOnPath(v9, v6));
        assertTrue(problem.occursOnPath(v2, v6));
        assertTrue(problem.occursOnPath(v6, v6));
        
        // make sure the correct equality test is being used
        assertTrue(problem.occursOnPath(v1Copy, v10));
        assertTrue(problem.occursOnPath(v7Copy, v10));
        assertTrue(problem.occursOnPath(v4Copy, v10));
        assertTrue(problem.occursOnPath(v10Copy, v10));
        
    }
    
    /**
     * This method tests the expand method by first expanding the initial
     * state represented by v1 and then selectively expanding children until
     * the the goal state represented by v6 is found.
     * At each step, assertions are made checking the correct size and
     * composition of the child lists produced by calling expand.
     * A helper method called find is used to choose among the child lists
     * which vertex to expand next.
     * This allows the method to test whether predecessor and distance information
     * is being kept correctly.
     * The final call to displayPath should display a FWGC problem solution.
     */
    @Test
    public void testExpand() {
        List<Vertex> children;
        
        v1.setDistance(0);
        
        children = problem.expand(v1);
        assertTrue(children.size()==1);
        assertTrue(children.contains(v7));
        
        children = problem.expand(find(v7, children));
        assertTrue(children.size()==1);
        assertTrue(children.contains(v4));
        
        children = problem.expand(find(v4, children));
        assertTrue(children.size()==2);
        assertTrue(children.contains(v10));
        assertTrue(children.contains(v8));
        
        children = problem.expand(find(v10, children));
        assertTrue(children.size()==1);
        assertTrue(children.contains(v3));
        
        children = problem.expand(find(v3, children));
        assertTrue(children.size()==1);
        assertTrue(children.contains(v9));
        
        children = problem.expand(find(v9, children));
        assertTrue(children.size()==2);
        assertTrue(children.contains(v2));
        assertTrue(children.contains(v5));
        
        Vertex v2Save = find(v2, children);
        
        children = problem.expand(find(v5, children));
        assertTrue(children.size()==1);
        assertTrue(children.contains(v8));
        
        children = problem.expand(find(v8, children));
        assertTrue(children.isEmpty());
        
        children = problem.expand(v2Save);
        assertTrue(children.size()==1);
        assertTrue(children.contains(v6));
        
        Vertex goal = find(v6, children);
        assertTrue(goal.getDistance()==7);
        displayPath(goal);
        
    }
    
    /**
     * Finds and returns a vertex from a list of vertices that is the same
     * as a given vertex v.
     * Identity criterion used is the equals method.
     * This method is used to find a vertex in a child set so that vertex
     * can be used for testing subsequent expansions in the testExpand method.
     * @param v
     * @param list
     * @return 
     */
    private static Vertex find(Vertex v, List<Vertex> list) {
        int i = list.indexOf(v);
        if ( i == -1 ) {
            return null;
        }
        else {
            return list.get(i);
        }
    }
    
    /**
     * Displays a predecessor path to the console by recursing to the start vertex
     * and then individually displaying vertices as the recursion is unwound.
     * @param v the terminating vertex in the predecessor path
     */
    private void displayPath(Vertex v) {
        if ( v != null ) {
            displayPath(v.getPredecessor());
            System.out.println("\n" + v.toString());
        }
    }

    @Test
    public void testSuccess() {
        assertTrue(!problem.success());
    }
}

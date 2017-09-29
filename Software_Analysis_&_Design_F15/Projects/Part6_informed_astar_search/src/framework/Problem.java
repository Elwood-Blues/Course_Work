package framework;

import graph.DequeAdder;
import graph.SimpleVertex;
import graph.Vertex;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This abstract class represents a problem in a problem solving domain.
 * Note that this class does not provide a constructor.
 * It provides getters and setters for the current state
 * of the problem, the list of moves for the problem, and the problem's
 * introduction string.
 * Extending classes need not have instance fields for these attributes, 
 * as they are inherited from this class.
 * Extending classes must set these values in their constructors using 
 * the setters (mutators) provided in this class.
 * Extending classes must also override the abstract <b>success</b> method.
 */
public abstract class Problem {

    /**
     * Determines whether the current state of this problem is a success.
     * Extending classes need to override this method.
     * @return whether the current state is a success
     */
    public abstract boolean success();
    
    
        /**
     * Checks to see if a vertex appears on the predecessor path
     * of an ancestor vertex.
     * @param v the vertex to check
     * @param ancestor the ancestor of v
     * @return true if v equals ancestor or any ancestor of ancestor
     */
    public boolean occursOnPath(Vertex v, Vertex ancestor) {
        if (ancestor == null) return false;
        else if (v.equals(ancestor)) return true;
        else {
            
            return occursOnPath(v, ancestor.getPredecessor());
            //return booReturn;
        }
        
//        boolean booReturn = false;
//        while (ancestor != null && !booReturn) {
//            booReturn = v.equals(ancestor);
//            ancestor = ancestor.getPredecessor();
//        }
//        return booReturn;
    }
    
    
        /**
     * Expands a vertex v in a state space search tree by creating a list
     * (its children) of all vertices adjacent to it in the state space.
     * The list may not include any vertex on the predecessor path 
     * leading to v.
     * Each child on the list has its predecessor set to v and its distance
     * from the root (its depth in the search tree) set to one more than v's 
     * distance.
     * @param v the vertex being expanded
     * @return a list of the children
     */
    public List<Vertex> expand(Vertex v) {
        List<Vertex> children = new LinkedList<Vertex>();
        for (Move move: moves)
        {
            Vertex child = (Vertex)move.doMove((State) v);
            if (child != null && !occursOnPath(child, v)) {
                child.setDistance(v.getDistance()+1);
                child.setPredecessor(v);
                children.add(child);
            }
            
        }
        return children;
    }
    
     /**
     * Searches for a certain gamestate.
     * The list may not include any vertex on the predecessor path 
     * leading to v.
     * Each child on the list has its predecessor set to v and its distance
     * from the root (its depth in the search tree) set to one more than v's 
     * distance.
     * @param start The start vertex for the search
     * @param adder A purely functional object that adds to either the 
     * head or tail of a double-ended queue
     */
     public Vertex search(Vertex start, DequeAdder adder) {
        start.setOpen(false);
        start.setDistance(0);

        
        Comparator<Vertex> myComp = new Comparator<Vertex>() {
            public int compare(Vertex v1, Vertex v2) {
                int h1 = ((State) v1).getHeuristic(getFinishState());
                int d1 = v1.getDistance();
                int h2 = ((State) v2).getHeuristic(getFinishState());
                int d2 = v2.getDistance();
                return (h1 + d1) - (h2 + d2);

            }
        };       
        
        
        PriorityQueue<Vertex> deque = new PriorityQueue<Vertex>(10000,myComp);
        deque.add(start);
        noDequeOps++;
        while ( !deque.isEmpty() ) {
            Vertex v = deque.remove();
            noDequeOps++;
            List<Vertex> adjList = expand(v);
            

           
       
            
            if (v.isSuccess(finishState)) {
                setSolutionLength((Integer) v.getDistance());
                
                Vertex ancestor = v;
                finishQueue.addFirst(v);
                
                for(int i = 0; i < getSolutionLength()-1; i++) {
                    ancestor = ancestor.getPredecessor();
                    finishQueue.addFirst(ancestor);
                }
                
                
                return v;
                

            }
            else {
                for (Vertex adjList1 : adjList) {
                    deque.add(adjList1);
                    noDequeOps++;
                }
                if(deque.size() > getMaxDequeSize()) maxDequeSize = deque.size();
            }
            
        }
     
        return null;
   
    }
    


    /**
     * Gets the current state of the problem.
     * @return the current state
     */
    public State getCurrentState() {
        return currentState;
    }
    

    /**
     * Sets the current state of the problem.
     * @param currentState the current state
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * Gets an explanatory introduction string for the problem.
     * @return the introduction string
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * Sets the introduction string for this problem.
     * @param introduction the introduction string
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * Gets the list of moves for this problem.
     * @return the list of moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Sets the list of moves for this problem.
     * @param moves the list of moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    
    /**
     * The current state of this problem
     */
    private State currentState;

    /**
     * The explanatory string for this problem.
     */
    private String introduction;

    /**
     * The list of moves for this problem.
     */
    private List<Move> moves;
    
     /**
     * The final state of the problem to search for.
     */
    private State finishState;
    
    /**
     * A list of possible solution states.
     */
    private List<State> solutionStates;
    
    /**
     * A list of possible starting states.
     */
    private List<State> startingStates;

    /**
     * Holds a list of canvases for the starting states.
     */
    private List<Canvas> startingCanvas;
    
    /**
     * Holds a list of canvases for the solution states.
     */
    private List<Canvas> solutionCanvas;
    
    /**
     * Holds a list of solution lengths.
     */
    private List<Integer> solutionLengths;
    
    /**
     * @param finishState the finishState to set
     */
    public void setFinishState(State finishState) {
        this.finishState = finishState;
    }
    
    public State getFinishState() {
        return finishState;
    }
    
    private LinkedList<Vertex> finishQueue = new LinkedList<Vertex>();
    private Integer solutionLength;
    private Integer noDequeOps = 0;
    private Integer maxDequeSize = 1;

    public Vertex finishQueuePeek() {
        return finishQueue.peek();
    }
    
    public Vertex finishQueuePop() {
        return finishQueue.pop();
    }
    
    /**
     * @return the solutionLength
     */
    public Integer getSolutionLength() {
        return solutionLength;
    }

    /**
     * @return the noDequeOps
     */
    public Integer getNoDequeOps() {
        return noDequeOps;
    }

    /**
     * @return the maxDequeSize
     */
    public Integer getMaxDequeSize() {
        return maxDequeSize;
    }
    
    /**
     * Method called which resets the integers for the solver ops.
     */
    public void resetSolverOps() {
        maxDequeSize = 0;
        noDequeOps = 0;
        setSolutionLength((Integer) 0);
    }

    /**
     * @return the solutionStates
     */
    public List<State> getSolutionStates() {
        return solutionStates;
    }

    /**
     * @param solutionStates the solutionStates to set
     */
    public void setSolutionStates(List<State> solutionStates) {
        this.solutionStates = solutionStates;
    }

    /**
     * @return the startingStates
     */
    public List<State> getStartingStates() {
        return startingStates;
    }

    /**
     * @param startingStates the startingStates to set
     */
    public void setStartingStates(List<State> startingStates) {
        this.startingStates = startingStates;
    }

    /**
     * @return the startingCanvas
     */
    public List<Canvas> getStartingCanvas() {
        return startingCanvas;
    }

    /**
     * @param startingCanvas the startingCanvas to set
     */
    public void setStartingCanvas(List<Canvas> startingCanvas) {
        this.startingCanvas = startingCanvas;
    }

    /**
     * @return the solutionCanvas
     */
    public List<Canvas> getSolutionCanvas() {
        return solutionCanvas;
    }

    /**
     * @param solutionCanvas the solutionCanvas to set
     */
    public void setSolutionCanvas(List<Canvas> solutionCanvas) {
        this.solutionCanvas = solutionCanvas;
    }

    /**
     * @return the solutionLengths
     */
    public List<Integer> getSolutionLengths() {
        return solutionLengths;
    }

    /**
     * @param solutionLengths the solutionLengths to set
     */
    public void setSolutionLengths(List<Integer> solutionLengths) {
        this.solutionLengths = solutionLengths;
    }

    /**
     * @param solutionLength the solutionLength to set
     */
    public void setSolutionLength(Integer solutionLength) {
        this.solutionLength = solutionLength;
    }
}

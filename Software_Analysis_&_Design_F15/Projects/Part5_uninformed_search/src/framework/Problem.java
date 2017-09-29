package framework;

import java.util.List;
import graph.Vertex;
import graph.DequeAdder;
import java.util.Deque;
import java.util.LinkedList;
import java.lang.StackOverflowError;
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
     * Checks to see if a vertex appears on the predecessor path
     * of an ancestor vertex.
     * @param v the vertex to check
     * @param ancestor the ancestor of v
     * @return true if v equals ancestor or any ancestor of ancestor
     */
    public boolean occursOnPath(Vertex v, Vertex ancestor) {
        boolean boolVal = false;
        /*try{
            if(ancestor == null)
            {
                boolVal = false;
                return boolVal;
            }
            else if(v.equals(ancestor))
            {
                boolVal = true;
                return boolVal;
            }
            else
            {
            return occursOnPath(v, ancestor.getPredecessor());
            }
        }
        catch (StackOverflowError e) {System.err.println("Stack Overflow: " + e.getMessage());}
        return boolVal;*/
        while(ancestor != null && !boolVal){
            boolVal = v.equals(ancestor);
            ancestor = ancestor.getPredecessor();
        }
        return boolVal;
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
        for(Move move : moves)
        {
            Vertex child = (Vertex)move.doMove((State) v);
            if( child != null && !occursOnPath(child, v) )
            {
                child.setDistance(v.getDistance()+1);
                child.setPredecessor(v);
                children.add(child);
            }
        }
        return children;
    }
    
    public Vertex search(Vertex start, DequeAdder adder){
        start.setOpen(false);
        start.setDistance(0);
        Deque<Vertex> deque = new LinkedList<Vertex>();
        adder.add(start, deque);
        while( !deque.isEmpty() ){
            Vertex v = deque.remove();
            noDequeOps++;
            List<Vertex> adjList = expand(v);
            
            if(v.isSuccess(finishState)){
                solutionLength = v.getDistance();
                
                Vertex ancestor = v;
                finishQueue.addFirst(v);
                
                for(int i = 0; i < solutionLength-1; i++){
                    ancestor = ancestor.getPredecessor();
                    finishQueue.addFirst(ancestor);
                }
                return v;
            }
            else{
                for(Vertex adjList1 : adjList){
                    adder.add(adjList1, deque);
                    noDequeOps++;
                }
                if(deque.size() > getMaxDequeSize()) maxDequeSize = deque.size();
            }
        }
        return null;
    }

    /**
     * Determines whether the current state of this problem is a success.
     * Extending classes need to override this method.
     * @return whether the current state is a success
     */
    public abstract boolean success();

    /**
     * Gets the current state of the problem.
     * @return the current state
     */
    public State getCurrentState() {
        return this.currentState;
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
        return this.introduction;
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
     * setter function for private finishState variable
     * @param finishState the finishState to set
     */
    public void setFinishState(State finishState){
        this.finishState = finishState;
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
     * the final state of a problem to search for
     */
    private State finishState;
    
    private LinkedList<Vertex> finishQueue = new LinkedList<Vertex>();
    private Integer solutionLength;
    private Integer noDequeOps = 0;
    private Integer maxDequeSize = 1;
    
    public Vertex finishQueuePeek(){
        return this.finishQueue.peek();
    }
    
    public Vertex finishQueuePop(){
        return this.finishQueue.pop();
    }
    
    public Integer getSolutionLength(){
        return this.solutionLength;
    }
    public Integer getNoDequeOps(){
        return this.noDequeOps;
    }
    public Integer getMaxDequeSize(){
        return this.maxDequeSize;
    }
    
    public void resetSolverOps(){
        this.maxDequeSize = 0;
        this.noDequeOps = 0;
        this.solutionLength = 0;
    }
}

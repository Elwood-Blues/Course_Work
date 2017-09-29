package blocks;

import framework.State;
import graph.SimpleVertex;
import java.util.Stack;

/**
 * This class represents a state of the Blocks World.
 * @author tcolburn
 */
public class BlockState extends SimpleVertex implements State {
    
    /**
     * A block state is represented by three stacks of characters, with the
     * top block on the stack top.
     * @param p the stack at place p
     * @param q the stack at place q
     * @param r  the stack at place r
     */
    public BlockState(Stack<Character> p, Stack<Character> q, Stack<Character> r) {
	this.p = p;
	this.q = q;
	this.r = r;
    }

    /**
     * A convenience constructor that allows stacks of blocks to be presented
     * as strings that are then converted to stacks of characters.
     * The first character of the string represents the top block of the stack.
     * @param p string representing place p
     * @param q string representing place q
     * @param r  string representing place r
     */
    public BlockState(String p, String q, String r) {
        this.p = stringToStack(p);
        this.q = stringToStack(q);
        this.r = stringToStack(r);
    }

    /**
     * A block state constructor that takes a state, a source place, 
     * and a target place, and creates a new state in which the block 
     * on top of the source is moved to the top of the target.
     * Precondition: the source place cannot be empty.
     * Note the use of clone() to copy the stacks.
     * @param state
     * @param source
     * @param target 
     */
    public BlockState(BlockState state, char source, char target) {
        p = (Stack<Character>)state.p.clone();
	q = (Stack<Character>)state.q.clone();
	r = (Stack<Character>)state.r.clone();
        Character block = source == 'p' ? p.pop() :
                          source == 'q' ? q.pop() : r.pop();
        Stack<Character> stack = target == 'p' ? p :
                                 target == 'q' ? q : r;
        stack.push(block);
    }

    /**
     * Indicates whether a place is empty in this state.
     * Precondition: the place must be either 'p', 'q', or 'r'.
     * @param place either 'p', 'q', or 'r'
     * @return true if the place is empty, false otherwise.
     */
    public boolean placeEmpty(char place) {

        if ( place == 'p' )
            if ( p.empty() )
                return true;
            else
                return false;
        else if ( place == 'q' )
            if ( q.empty() )
                return true;
            else
                return false;
        else  // place must be 'r'
            if ( r.empty() )
                return true;
            else
                return false;
    }

    /**
     * Getter for the stack at place p. 
     * Provided primarily for the BlockCanvas class
     * @return the stack at place p
     */
    public Stack<Character> getP() {
        return p;
    }

    /**
     * Getter for the stack at place q. 
     * Provided primarily for the BlockCanvas class
     * @return the stack at place q
     */
    public Stack<Character> getQ() {
        return q;
    }

    /**
     * Getter for the stack at place r. 
     * Provided primarily for the BlockCanvas class
     * @return the stack at place r
     */
    public Stack<Character> getR() {
        return r;
    }
    
    /**
     * Checks for equality between this block state and another object.
     * @param other the other object
     * @return true if all corresponding stacks are equal, false otherwise
     */
    public boolean equals(Object other) {
	if (this == other) return true;
	if (other == null) return false;
	if (getClass() != other.getClass()) return false;
	BlockState state = (BlockState) other;
	return (p.equals(state.p) &&
		q.equals(state.q) &&
		r.equals(state.r));
    }

    /**
     * Creates and returns a string representation of this block state.
     * @return a string representation of this block state
     */
    public String toString() {

        StringBuffer buf = new StringBuffer();

        int maxSize = Math.max(Math.max(p.size(), q.size()), r.size());

	for (int i = maxSize-1; i >= 0; i--) {
	    Character pBlock = getBlock(p,i);
	    Character qBlock = getBlock(q,i);
	    Character rBlock = getBlock(r,i);
            buf.append(" " + pBlock + " " + qBlock + " " + rBlock + " \n");
	}

        buf.append(" ----- \n p q r ");
        return buf.toString();
    }

    private static Character getBlock(Stack<Character> s, int index) {
	Character block = ' ';
	try {
	    block = ((Character)s.elementAt(index));
	}
	catch(ArrayIndexOutOfBoundsException ex) { }
	return block;
    }

    /**
     * Computes and returns a heuristic estimate of the number of moves from 
     * this state to the goal state.
     * @param goal the goal state for the problem currently being solved
     * @return the heuristic estimate of the number of moves to the goal
     */
    public int getHeuristic(State goal) {
        BlockState blockGoal = (BlockState) goal;
	int heuristic = 0;
        
        for(int i = 0; i < this.getP().size(); i++) {
            if(blockGoal.getQ().contains(this.getP().get(i))) {
                heuristic += 1;
                heuristic += blockGoal.getQ().indexOf(this.getP().get(i));
                
            }
            else if(blockGoal.getR().contains(this.getP().get(i))) {
                heuristic += 1;
                heuristic += blockGoal.getR().indexOf(this.getP().get(i));
            }
            else {
                if(i >= blockGoal.getP().size()) {
                    heuristic += 2;
                }
                else
                if(!(blockGoal.getP().get(i).equals(this.getP().get(i)))) {
                    heuristic += 2+i;
                }
            }
                
            
        }
        
        for(int i = 0; i < this.getQ().size(); i++) {
            if(blockGoal.getP().contains(this.getQ().get(i))) {
                heuristic += 1;
                heuristic += blockGoal.getP().indexOf(this.getQ().get(i));                
            }
            else if(blockGoal.getR().contains(this.getQ().get(i))) {
                heuristic += 1;
                heuristic += blockGoal.getR().indexOf(this.getQ().get(i));
            }
            else {
                if(i >= blockGoal.getQ().size()) {
                    heuristic += 2;
                }
                else
                if(!(blockGoal.getQ().get(i).equals(this.getQ().get(i)))) {
                    heuristic += 2+i;
                }
            }            
        }
        
        for(int i = 0; i < this.getR().size(); i++) {
            if(blockGoal.getP().contains(this.getR().get(i))) {
                heuristic += 1;
                heuristic += blockGoal.getP().indexOf(this.getR().get(i));
            }
            else if(blockGoal.getQ().contains(this.getR().get(i))) {
                heuristic += 1;
                heuristic += blockGoal.getQ().indexOf(this.getR().get(i));                
            }
            else {
                if(i >= blockGoal.getR().size()) {
                    heuristic += 2;
                }
                else
                if(!(blockGoal.getR().get(i).equals(this.getR().get(i)))) {
                    heuristic += 2+i;
                }
            }           
        }        
        
        return heuristic;
    }

    public int hashCode() {
        // You must write for extra credit
        return 0;
    }

    /**
     * Helper method that converts a string to a stack of characters.
     * @param string a string representing a stack of blocks on a place
     * @return a stack of characters with the top character corresponding
     * to the first character in the string
     */
    private static Stack<Character> stringToStack(String string) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = string.length()-1; i >= 0; i--) {
            stack.push(string.charAt(i));
        }
        return stack;
    }
    
     /**
     * Tests this block state with a known successful BlockState.
     * If the current state is equal to the passed in state and the time is less,
     * than return true;
     * @param other the BlockState representing a successful state.
     * @return true if this state is a successful state.
     */
    @Override
    public boolean isSuccess(Object other) {
        BlockState otherBlock = (BlockState)other;
        boolean isGood = true;
        isGood = equals(otherBlock);
        
        return isGood;
    }

    private Stack<Character> p;
    private Stack<Character> q;
    private Stack<Character> r;
}

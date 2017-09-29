/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import framework.State;
import graph.*;

/**
 *
 * @author Peter
 */
public class PuzzleState extends SimpleVertex implements State {


    /**
     * Constructor for Puzzle State, creates a new water jug state
     * @param space1 value for the respective space
     * @param space2    ""
     * @param space3    ""
     * @param space4    ""
     * @param space5    ""
     * @param space6    ""
     * @param space7    ""
     * @param space8    ""
     * @param space9    ""
     */
    public PuzzleState(int space1,
            int space2,
            int space3,
            int space4,
            int space5,
            int space6,
            int space7,
            int space8,
            int space9) {
        
        spaces = new int[9];
        
        spaces[0] = space1;
        spaces[1] = space2;
        spaces[2] = space3;
        spaces[3] = space4;
        spaces[4] = space5;
        spaces[5] = space6;
        spaces[6] = space7;
        spaces[7] = space8;
        spaces[8] = space9;
        
        
    }
    
    /**
     * Tests this puzzle state with another for equality.
     * @param other the other puzzle state to be tested against this one.
     * @return true if this state is equal to the other state, false otherwise
     */
    public boolean equals(PuzzleState other) {
        boolean isEqual = true;
        int i = 0;
        
        while(isEqual && i < 9) {
            if(other.getPieceInSpace(i) != this.getPieceInSpace(i)) isEqual = false;
            ++i;
        }

        
        return isEqual;
    }
    
    /**
     * Creates a string representation of this state for display to the user
     * trying to solve the problem.
     * Not yet implemented.
     */
    @Override
    public String toString() {
        return null;
    }
    
    /**
     * An integer array corresponding to spaces on the board..
     * 0 index is the empty space.  Each other spot shows an index.
     */
    private int[] spaces;

    /**
     * 
     * @param index the index to return
     * @return the value located at index
     */
    public int getPieceInSpace(int index) {
        return spaces[index];
    }

    public int getSpacePieceIn(int index) {
        for(int i=0; i<9; i++) {
            if (getPieceInSpace(i) == index) return i;
          
        }
        return -1;
    }
@Override
    public boolean isSuccess(Object object) {
        PuzzleState otherObject = (PuzzleState) object;
        return equals(otherObject);
    }
}

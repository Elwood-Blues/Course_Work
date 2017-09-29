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
    
    @Override
    public int getHeuristic(State goal){
        return tilesOutOfPlace(goal);
    }
    
    public int tilesOutOfPlace(State goal){
        int counter = 0;
        PuzzleState goalPuzzle = (PuzzleState) goal;
        int[][] manhattans = new int[9][9];
        
        manhattans[0][0] = 0;
        manhattans[0][1] = 1;
        manhattans[0][2] = 2;
        manhattans[0][3] = 1;
        manhattans[0][4] = 2;
        manhattans[0][5] = 3;
        manhattans[0][6] = 2;
        manhattans[0][7] = 3;
        manhattans[0][8] = 4;
        
        manhattans[1][0] = 1;
        manhattans[1][1] = 0;
        manhattans[1][2] = 1;
        manhattans[1][3] = 2;
        manhattans[1][4] = 1;
        manhattans[1][5] = 2;
        manhattans[1][6] = 3;
        manhattans[1][7] = 2;
        manhattans[1][8] = 3;
        
        manhattans[2][0] = 2;
        manhattans[2][1] = 1;
        manhattans[2][2] = 0;
        manhattans[2][3] = 3;
        manhattans[2][4] = 2;
        manhattans[2][5] = 1;
        manhattans[2][6] = 4;
        manhattans[2][7] = 3;
        manhattans[2][8] = 2;
        
        manhattans[3][0] = 1;
        manhattans[3][1] = 2;
        manhattans[3][2] = 3;
        manhattans[3][3] = 0;
        manhattans[3][4] = 1;
        manhattans[3][5] = 2;
        manhattans[3][6] = 1;
        manhattans[3][7] = 2;
        manhattans[3][8] = 3;
        
        manhattans[4][0] = 2;
        manhattans[4][1] = 1;
        manhattans[4][2] = 2;
        manhattans[4][3] = 1;
        manhattans[4][4] = 0;
        manhattans[4][5] = 1;
        manhattans[4][6] = 2;
        manhattans[4][7] = 1;
        manhattans[4][8] = 2;
        
        manhattans[5][0] = 3;
        manhattans[5][1] = 2;
        manhattans[5][2] = 1;
        manhattans[5][3] = 2;
        manhattans[5][4] = 1;
        manhattans[5][5] = 0;
        manhattans[5][6] = 3;
        manhattans[5][7] = 2;
        manhattans[5][8] = 1;
        
        manhattans[6][0] = 2;
        manhattans[6][1] = 3;
        manhattans[6][2] = 4;
        manhattans[6][3] = 1;
        manhattans[6][4] = 2;
        manhattans[6][5] = 3;
        manhattans[6][6] = 0;
        manhattans[6][7] = 1;
        manhattans[6][8] = 2;
        
        manhattans[7][0] = 3;
        manhattans[7][1] = 2;
        manhattans[7][2] = 3;
        manhattans[7][3] = 2;
        manhattans[7][4] = 1;
        manhattans[7][5] = 2;
        manhattans[7][6] = 1;
        manhattans[7][7] = 0;
        manhattans[7][8] = 1;
        
        manhattans[8][0] = 4;
        manhattans[8][1] = 3;
        manhattans[8][2] = 2;
        manhattans[8][3] = 3;
        manhattans[8][4] = 2;
        manhattans[8][5] = 1;
        manhattans[8][6] = 2;
        manhattans[8][7] = 1;
        manhattans[8][8] = 0;
        
        for(int i = 0; i < 9; i++){
            if(getPieceInSpace(i) != 0){
                int j = 0;
                boolean found = false;
                while(!found){
                    found = getPieceInSpace(i) == goalPuzzle.getPieceInSpace(j);
                    if(found) counter += manhattans[i][j];
                    j++;
                }
            }
        }
        return counter;
    }
}

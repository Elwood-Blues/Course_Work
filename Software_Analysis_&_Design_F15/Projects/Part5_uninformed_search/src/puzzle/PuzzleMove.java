/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import framework.Move;
import framework.State;

/**
 *
 * @author Peter
 */
public class PuzzleMove extends Move {
    
    /**
     * Constructor for PuzzleMove, creates a WaterJugMove object
     * uses super() to initialize with WaterJugMove constructor
     * @param moveName the name of this move
     */
    public PuzzleMove(String moveName){
        super(moveName);
        moveType = moveName;
    }
    /**
     * Attempts to perfrom the move on a given puzzle state
     * @param otherState the puzzle state on which this move is to be performed
     * @return a new puzzle state reflecting the move
     */
    @Override
    public State doMove(State otherState){
        PuzzleState state = (PuzzleState) otherState;
        int moveNumber = moveType.codePointAt(11)-48;
        int index = state.getSpacePieceIn(moveNumber);
        
        PuzzleState newState;
        
        if(isNeighbor(state.getSpacePieceIn(moveNumber), state.getSpacePieceIn(0))){
            int[] newSpaces = new int[9];
            for(int i = 0; i < 9; i++){
                if( i == state.getSpacePieceIn(moveNumber)) newSpaces[i] = 0;
                else if(i == state.getSpacePieceIn(0)) newSpaces[i] = moveNumber;
                else newSpaces[i] = state.getPieceInSpace(i);
            }
            
            newState = new PuzzleState(newSpaces[0], newSpaces[1], newSpaces[2],
                            newSpaces[3], newSpaces[4], newSpaces[5], newSpaces[6],
                            newSpaces[7], newSpaces[8]);
        }
        else newState = null;
        return newState;
    }
    
    //private section
    private final String moveType;
    
    private boolean isNeighbor(int space1, int space2){
        boolean areNeighbors = false;
        
        switch(space1) {
            case 0:
                areNeighbors = space2 == 1 || space2 == 3;
                break;
            case 1:
                areNeighbors = space2 == 0 || space2 == 2 || space2 == 4;
                break;
            case 2:
                areNeighbors = space2 == 1 || space2 == 5;
                break;
            case 3:
                areNeighbors = space2 == 0 || space2 == 4 || space2 == 6;
                break;
            case 4:
                areNeighbors = space2 == 3 || space2 == 1 || space2 == 5 || space2 == 7;
                break;
            case 5:
                areNeighbors = space2 == 2 || space2 == 4 || space2 == 8;
                break;
            case 6:
                areNeighbors = space2 == 3 || space2 == 7;
                break;
            case 7:
                areNeighbors = space2 == 4 || space2 == 6 || space2 == 8;
                break;
            case 8:
                areNeighbors = space2 == 7 || space2 == 5;
                break;
        }
        return areNeighbors;
    }
}

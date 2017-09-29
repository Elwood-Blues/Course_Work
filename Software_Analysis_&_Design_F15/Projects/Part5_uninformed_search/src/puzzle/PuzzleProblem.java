/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import framework.Move;
import framework.Problem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Peter
 */
public class PuzzleProblem extends Problem {
    
    public PuzzleProblem(){
        setIntroduction("Welcome to the 8-Piece Puzzle Problem.\n\nYou are given"
                + "a 3x3 grid of puzzle pieces with only one blank space.  You must "
                + "arrange the tiles so they form a 1-8 ring around the outside of "
                + "the grid.  Starting in the top left corner.  For Example: \n"
                + "| 1 | 2 | 3 |\n| 8 |    | 4 |\n| 7 | 6 | 5 |\n"
                + "wins the game.");
        PuzzleState tempState = new PuzzleState(2,8,3,1,6,4,7,0,5);
        
        setCurrentState(tempState);
        List<Move> moveList = new ArrayList<Move>();
        
        PuzzleMove m1 = new PuzzleMove("Move Piece 1");
        PuzzleMove m2 = new PuzzleMove("Move Piece 2");
        PuzzleMove m3 = new PuzzleMove("Move Piece 3");
        PuzzleMove m4 = new PuzzleMove("Move Piece 4");
        PuzzleMove m5 = new PuzzleMove("Move Piece 5");
        PuzzleMove m6 = new PuzzleMove("Move Piece 6");
        PuzzleMove m7 = new PuzzleMove("Move Piece 7");
        PuzzleMove m8 = new PuzzleMove("Move Piece 8");
        
        moveList.add(m1);
        moveList.add(m2);
        moveList.add(m3);
        moveList.add(m4);
        moveList.add(m5);
        moveList.add(m6);
        moveList.add(m7);
        moveList.add(m8);
        
        setMoves(moveList);
        
        WinningSet[0] = 1;
        WinningSet[1] = 2;
        WinningSet[2] = 3;
        WinningSet[3] = 8;
        WinningSet[4] = 0;
        WinningSet[5] = 4;
        WinningSet[6] = 7;
        WinningSet[7] = 6;
        WinningSet[8] = 5;
        
        setFinishState(new PuzzleState(1,2,3,8,0,4,7,6,5));
    }
    
    @Override
    public boolean success(){
        boolean isSuccess = true;
        
        PuzzleState cur = (PuzzleState) getCurrentState();
        for(int i = 0; i < 9; i++){
            if(cur.getPieceInSpace(i) != WinningSet[i]) isSuccess = false;
        }
        return isSuccess;
    }
    // private section
    private int[] WinningSet = new int[9];
}

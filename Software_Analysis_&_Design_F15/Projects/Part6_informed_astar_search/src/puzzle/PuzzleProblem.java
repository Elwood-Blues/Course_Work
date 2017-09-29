/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import framework.Move;
import framework.Problem;
import framework.State;
import framework.Canvas;
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
        
        List<State> startStates = new ArrayList<State>();
        startStates.add(new PuzzleState(2,8,3,1,6,4,7,0,5));
        startStates.add(new PuzzleState(3,6,4,1,0,2,8,7,5));
        startStates.add(new PuzzleState(3,0,4,1,6,5,8,2,7));
        startStates.add(new PuzzleState(2,1,3,8,0,4,6,7,5));
        startStates.add(new PuzzleState(4,2,0,8,3,6,7,5,1));
        startStates.add(new PuzzleState(1,6,3,4,0,8,7,2,5));
        startStates.add(new PuzzleState(5,2,7,8,0,4,3,6,1));
        startStates.add(new PuzzleState(5,6,7,4,0,8,3,2,1));
        super.setStartingStates(startStates);
        
        List<State> solnStates = new ArrayList<State>();
        for(int i = 0; i < startStates.size(); i++){
            solnStates.add(new PuzzleState(1,2,3,8,0,4,7,6,5));
        }
        super.setSolutionStates(solnStates);
        
        List<Canvas> passCanvas1 = new ArrayList<Canvas>();
        for(int i = 0; i < startStates.size(); i++){
            passCanvas1.add(new PuzzleCanvas(startStates.get(i),100));
        }
        super.setStartingCanvas(passCanvas1);
        
        List<Canvas> passCanvas2 = new ArrayList<Canvas>();
        for(int i = 0; i < solnStates.size(); i++){
            passCanvas2.add(new PuzzleCanvas(solnStates.get(i), 100));
        }
        super.setSolutionCanvas(passCanvas2);
        
        List<Integer> solutionLengths = new ArrayList<Integer>();
        solutionLengths.add(5);
        solutionLengths.add(10);
        solutionLengths.add(13);
        solutionLengths.add(18);
        solutionLengths.add(20);
        solutionLengths.add(24);
        solutionLengths.add(30);
        solutionLengths.add(30);
        super.setSolutionLengths(solutionLengths);
        
        
        super.setCurrentState(super.getStartingStates().get(0));
        super.setFinishState(super.getSolutionStates().get(0));
        
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
        
//        WinningSet[0] = 1;
//        WinningSet[1] = 2;
//        WinningSet[2] = 3;
//        WinningSet[3] = 8;
//        WinningSet[4] = 0;
//        WinningSet[5] = 4;
//        WinningSet[6] = 7;
//        WinningSet[7] = 6;
//        WinningSet[8] = 5;
//        
//        setFinishState(new PuzzleState(1,2,3,8,0,4,7,6,5));
    }
    
    @Override
    public boolean success(){
        boolean isSuccess = true;
        
        PuzzleState cur = (PuzzleState) getCurrentState();
        PuzzleState fin = (PuzzleState) getFinishState();
        for(int i = 0; i < 8; i++){
            if(cur.getPieceInSpace(i) != fin.getPieceInSpace(i)) isSuccess = false;
        }
        return isSuccess;
    }
    // private section
    //private int[] WinningSet = new int[9];
}

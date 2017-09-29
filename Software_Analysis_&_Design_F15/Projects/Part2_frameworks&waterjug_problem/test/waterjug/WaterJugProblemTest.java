package waterjug;

import java.util.List;
import framework.Problem;
import framework.State;
import framework.Move;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the WaterJugProblem class.
 * You should use the BridgeProblemTest class as a model.
 * @author your name here
 */
public class WaterJugProblemTest {
    
    // You should use the BridgeProblemTest class as a model for setting up
    // the tests with private instance fields and methods here.
    private Problem problem = new WaterJugProblem();
    private List<Move> moves = problem.getMoves();
    
    /**
     * Private Method to search the move list for a match of the input
     * @param moveName the name of the desired move(input)
     * @return the move with the given name, or null if not found
     */
    private Move findMove(String moveName){
        for(Move move: moves){
            if(move.getMoveName().equals(moveName))
                return move;
        }
        return null;
    }
    
    WaterJugMove fillX = new WaterJugMove("Fill Jug X");
    WaterJugMove fillY = new WaterJugMove("Fill Jug Y");
    WaterJugMove emptyX = new WaterJugMove("Empty Jug X");
    WaterJugMove emptyY = new WaterJugMove("Empty Jug Y");
    WaterJugMove XtoY = new WaterJugMove("Transfer Jug X to Jug Y");
    WaterJugMove YtoX = new WaterJugMove("Transfer Jug Y to Jug X");
    
    /**
     * Method to try to perform a move on the current state of the problem
     * @param move = the move to try
     */
    private void tryMove(Move move){
        State state = problem.getCurrentState();
        State next = move.doMove(state);
        problem.setCurrentState(next);
    }
    /**
     * Tests to be sure the problem represents all the moves.
     */
    @Test
    public void testMoves() {
        assertTrue(fillX != null);
        assertTrue(fillY != null);
        assertTrue(emptyX != null);
        assertTrue(emptyY != null);
        assertTrue(XtoY != null);
        assertTrue(YtoX != null);
    }

    /**
     * Tests the 4-move solution to the water jug problem
     */
    @Test
    public void testSolution1() {
        WaterJugState newState = new WaterJugState(0,0);
        problem.setCurrentState(newState);
        WaterJugState curState = (WaterJugState) problem.getCurrentState();
        
        curState = (WaterJugState)fillX.doMove(curState);
        curState = (WaterJugState)XtoY.doMove(curState);
        curState = (WaterJugState)fillX.doMove(curState);
        curState = (WaterJugState)XtoY.doMove(curState);
        
        problem.setCurrentState(curState);
        assertTrue(problem.success());
    }

    /**
     * Tests the 6-move solution to the water jug problem
     */
    @Test
    public void testSolution2() {
        WaterJugState newState = new WaterJugState(0,0);
        problem.setCurrentState(newState);
        WaterJugState curState = (WaterJugState) problem.getCurrentState();
        
        curState = (WaterJugState)fillY.doMove(curState);
        curState = (WaterJugState)YtoX.doMove(curState);
        curState = (WaterJugState)emptyX.doMove(curState);
        curState = (WaterJugState)YtoX.doMove(curState);
        curState = (WaterJugState)fillY.doMove(curState);
        curState = (WaterJugState)YtoX.doMove(curState);
        
        problem.setCurrentState(curState);
        assertTrue(problem.success());
    }
    
    /**
     * Tests the problem's introduction string.
     */
    @Test
    public void testIntro() {
        assertTrue(problem.getIntroduction().equals("Welcome to the Water Jug Problem.\n\n"
            + "You are given two emtpy jugs: jug X holds 3 gallons, jug Y holds 4.\n"
            + "Neither has any measuring markers on it.  You have a ready supply\n"
            + "of water.  You can fill either jug, empty either jug on the ground,\n"
            + "or pour all or some of either jug into the other.  The goal is to\n"
            + "get exactly 2 gallons of water into either jug."));
    }
}

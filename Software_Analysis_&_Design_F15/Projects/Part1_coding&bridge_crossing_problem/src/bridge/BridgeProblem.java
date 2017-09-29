package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Bridge Crossing problem.
 * It provides an introductory message describing the problem,
 * stores the problem's possible moves and current state, and
 * tests for whether the problem has been successfully solved.
 * @author Peter Braband #3418868 Section 12:00 class 2511
 */
public class BridgeProblem {
    /**
     * An ArrayList of all the possible bridge moves.
     */
    private List<BridgeMove> moveList = new ArrayList<BridgeMove>();
    
    /**
     * Holds the current state across methods.
     */
    private BridgeState curState;
    
    /**
     * The bridge problem constructor should create the initial bridge state
     * object and store it as the problem's current state.
     * It should also create the 10 valid bridge move objects and store them
     * on an accessible list.
     */
    public BridgeProblem() {
        curState = new BridgeState(Position.WEST,Position.WEST,
                Position.WEST,Position.WEST,Position.WEST,0);
        
        BridgeMove p1Only = new BridgeMove("P1 crosses alone");
        BridgeMove p2Only = new BridgeMove("P2 crosses alone");
        BridgeMove p5Only = new BridgeMove("P5 crosses alone");
        BridgeMove p10Only = new BridgeMove("P10 crosses alone");
        BridgeMove p1andP2 = new BridgeMove("P1 crosses with P2");
        BridgeMove p1andP5 = new BridgeMove("P1 crosses with P5");
        BridgeMove p1andP10 = new BridgeMove("P1 crosses with P10");
        BridgeMove p2andP5 = new BridgeMove("P2 crosses with P5");
        BridgeMove p2andP10 = new BridgeMove("P2 crosses with P10");
        BridgeMove p5andP10 = new BridgeMove("P5 crosses with P10");
        
        moveList.add(p1Only);
        moveList.add(p2Only);
        moveList.add(p5Only);
        moveList.add(p10Only);
        moveList.add(p1andP2);
        moveList.add(p1andP5);
        moveList.add(p1andP10);
        moveList.add(p2andP5);
        moveList.add(p2andP10);
        moveList.add(p5andP10);
    }
    
    /**
     * Getter (accessor) for this problem's introduction string.
     * @return the introduction string
     */
    public String getIntroduction() {
        return "Welcome to the Bridge Crossing Problem.\n\n" +
                "Four persons, P1, P2, P5 & P10 are on the west side of the bridge crossing a river.\n" +
                "Only one or two persons can cross at a time because it is dark,\n" +
                "and the flashlight must be taken on every crossing.\n" +
                "When two people cross, they travel at the speed of the slowest person.\n" +
                "Devise a sequence of crossings so that all four people get across\n" +
                "the bridge in no more than 17 minutes.\n\n";
    }

    /**
     * Getter (accessor) for this problem's list of valid bridge move objects.
     * @return the list of bridge moves
     */
    public List<BridgeMove> getMoves() {
        return moveList; // You must provide
    }
    
    /**
     * Tests for whether the current state of this problem indicates that the
     * problem has been successfully solved.
     * @return true if the problem has been solved, false otherwise
     */
    public boolean success() {
        boolean success = true;
        if (curState.getP1Position() == Position.WEST) success = false;
        if (curState.getP2Position() == Position.WEST) success = false;
        if (curState.getP5Position() == Position.WEST) success = false;
        if (curState.getP10Position() == Position.WEST) success = false;
        if (curState.getTimeSoFar() > 17) success = false;
        
        return success;
    }

    /**
     * Getter (accessor) for this problem's current bridge state.
     * @return the current state
     */
    public BridgeState getCurrentState() {
        return curState;  // You must provide
    }

    /**
     * Setter (mutator) for this problem's current bridge state.
     * @param currentState the state to be made this problem's current state
     */
    public void setCurrentState(BridgeState currentState) {
        curState = currentState;
    }
    
}

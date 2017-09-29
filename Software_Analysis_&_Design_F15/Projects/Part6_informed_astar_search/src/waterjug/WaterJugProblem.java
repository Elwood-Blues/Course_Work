package waterjug;

import framework.Move;
import framework.Problem;
import framework.State;
import framework.Canvas;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Water Jug problem.
 * It provides an introductory message describing the problem,
 * stores the problem's possible moves and current state, and
 * checks for whether the problem has been successfully solved.
 * Note that this class extends the abstract class <b>Problem</b> and
 * therefore imports <b>framework.Problem</b>.
 * This class inherits the <b>setIntroduction(), setCurrentState()</b>, and
 * <b>setMoves()</b> methods from its parent
 * and thus it should not have any instance fields for these attributes.
 * @author Peter Braband
 */
public class WaterJugProblem extends Problem {

    /**
     * Constructs a new water jug problem object.  A new water jug state object
     * should be constructed with zero gallons in both jugs.
     * This state should be set as the current state of the problem using
     * the inherited <b>setCurrentState()</b> method.
     * The six valid water jug moves should be created and stored on a list using
     * the inherited <b>setMoves()</b> method.
     * The appropriate introduction string for this problem should be stored
     * using the inherited <b>setIntroduction()</b>.
     */
    public WaterJugProblem() {
        setIntroduction(WaterJugIntro);
        
        List<State> startStates = new ArrayList<State>();
        startStates.add(new WaterJugState(0,0));
        super.setStartingStates(startStates);
        
        List<State> solnStates = new ArrayList<State>();
        solnStates.add(new WaterJugState(2,0));
        super.setSolutionStates(solnStates);
        
        List<Canvas> passCanvas1 = new ArrayList<Canvas>();
        for(int i = 0; i < startStates.size(); i++){
            passCanvas1.add(new WaterJugCanvas(startStates.get(i),100));
        }
        super.setStartingCanvas(passCanvas1);
        
        List<Canvas> passCanvas2 = new ArrayList<Canvas>();
        for(int i = 0; i < solnStates.size(); i++){
            passCanvas2.add(new WaterJugCanvas(solnStates.get(i),100));
        }
        super.setSolutionCanvas(passCanvas2);
        
        List<Integer> solutionLengths = new ArrayList<Integer>();
        solutionLengths.add(4);
        super.setSolutionLengths(solutionLengths);
        
        super.setCurrentState(super.getStartingStates().get(0));
        super.setFinishState(super.getSolutionStates().get(0));
        
        List<Move> moveList = new ArrayList<Move>();
        
        WaterJugMove fillX = new WaterJugMove("Fill Jug X");
        WaterJugMove fillY = new WaterJugMove("Fill Jug Y");
        WaterJugMove emptyX = new WaterJugMove("Empty Jug X");
        WaterJugMove emptyY = new WaterJugMove("Empty Jug Y");
        WaterJugMove XtoY = new WaterJugMove("Transfer Jug X to Jug Y");
        WaterJugMove YtoX = new WaterJugMove("Transfer Jug Y to Jug X");
        
        moveList.add(fillX);
        moveList.add(fillY);
        moveList.add(emptyX);
        moveList.add(emptyY);
        moveList.add(XtoY);
        moveList.add(YtoX);
        
        setMoves(moveList);
        
        setFinishState(new WaterJugState(2,0));
    }

    /**
     * Returns whether the current state of this problem is a success.
     * Note that this method implements the abstract <b>success</b> method declared
     * in the parent.
     * Note also that the current state of the problem must be gotten using the
     * inherited <b>getCurrentState()</b> method.
     * Since that method returns a value of type <b>State</b>, it must be cast to
     * <b>WaterJugState</b> before processing.
     * The current state is a success if either jug has 2 gallons.
     * @return <b>true</b> if the current state is a success, <b>false</b> otherwise
     */
    public boolean success() {

	WaterJugState cur = (WaterJugState)getCurrentState();
        if(cur.getJugAmount(0) == 2 || cur.getJugAmount(1) == 2)
            return true;
        else
            return false;
    }
    
    // Private instance fields are not necessary since they are inherited.
    private String WaterJugIntro = ("Welcome to the Water Jug Problem.\n\n"
            + "You are given two emtpy jugs: jug X holds 3 gallons, jug Y holds 4.\n"
            + "Neither has any measuring markers on it.  You have a ready supply\n"
            + "of water.  You can fill either jug, empty either jug on the ground,\n"
            + "or pour all or some of either jug into the other.  The goal is to\n"
            + "get exactly 2 gallons of water into either jug.");
}

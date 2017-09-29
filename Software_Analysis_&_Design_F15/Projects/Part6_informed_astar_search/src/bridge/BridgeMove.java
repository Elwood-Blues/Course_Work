package bridge;

import framework.Move;
import framework.State;

/**
 * This class represents moves in the Bridge Crossing problem.
 * A move object stores its move name and knows how to apply itself
 * to a bridge state to create a new state representing the result
 * of the move.
 * Note that this class extends the abstract class <b>Move</b> and
 * therefore imports <b>framework.Move</b>.
 * This class inherits the <b>getMoveName()</b> method from its parent
 * and thus it should not have an instance field for the move name.
 * Other than that, it can be essentially the same as in the previous
 * assignment.
 * @author Peter Braband
 */
public class BridgeMove extends Move {

    /**
     * Constructs a new bridge move object.
     * Note that the move name is passed to the parent constructor
     * using <b>super</b>.
     * @param moveName the name of this move.
     * It is an error if the name is not one of the following:
     * <ul>
     * <li> "P1 crosses alone" </li>
     * <li> "P2 crosses alone" </li>
     * <li> "P5 crosses alone" </li>
     * <li> "P10 crosses alone" </li>
     * <li> "P1 crosses with P2" </li>
     * <li> "P1 crosses with P5" </li>
     * <li> "P1 crosses with P10" </li>
     * <li> "P2 crosses with P5" </li>
     * <li> "P2 crosses with P10" </li>
     * <li> "P5 crosses with P10" </li>
     * </ul>
     */
    public BridgeMove(String moveName) {
        super(moveName);
        moveType = moveName;
    }
    
    /**
     * Attempts to perform this move on a given bridge state.
     * Note that this method implements the abstract <b>doMove</b> method declared
     * in the parent.
     * Thus the argument of type <b>State</b> must be cast to type
     * <b>BridgeState</b> before processing.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new bridge state object is returned that
     * reflects this move.
     * A move cannot be performed if the flashlight is not on the same side
     * as the crossing person(s), or if a pair of persons who are crossing are not
     * on the same side.
     * If the move cannot be performed <b>null</b> is returned.
     * @param otherState the bridge state on which this move is to be performed
     * @return a new bridge state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public State doMove(State otherState) {
        BridgeState state = (BridgeState) otherState;
        Position newP1 = state.getP1Position();
        Position newP2 = state.getP2Position();
        Position newF = state.getFlashlightPosition();
        Position newP5 = state.getP5Position();
        Position newP10 = state.getP10Position();
        Position newFlash = state.getFlashlightPosition();
        int timeToAdd = 0;
        
        
        if(moveType.contains("P1 "))
        {
            if(checkVsFlash(state.getP1Position(), state) )
            {
                newP1 = switchPos(newP1);
                timeToAdd = 1;
            }
            else
                return null;
        }
        if (moveType.contains("P2 ") || moveType.contains(" P2")){
            if(checkVsFlash(state.getP2Position(), state) )
            {
                newP2 = switchPos(newP2);
                timeToAdd = 2;
            }
            else
                return null;
        }
        if( moveType.contains("P5 ") || moveType.contains(" P5"))
        {
            if(checkVsFlash(state.getP5Position(), state))
            {
                newP5 = switchPos(newP5);
                timeToAdd = 5;
            }
            else
                return null;
        }
        if( moveType.contains("P10 ") || moveType.contains(" P10"))
        {
            if(checkVsFlash(state.getP10Position(), state))
            {
                newP10 = switchPos(newP10);
                timeToAdd = 10;
            }
            else
                return null;
        }
        
        newFlash = switchPos(newFlash);
        BridgeState newState = new BridgeState(newP1, newP2, newFlash, newP5,
                                newP10, state.getTimeSoFar() + timeToAdd);
        return newState;
    }
    
    // Private methods and instance fields should go here
    /**
     * String to store name of move for other methods to reference
     */
    private final String moveType;
    
    /**
     * Method to swap position of the variable passed in
     * Checks the position passed in and returns the opposite
     * @param old the old position to check
     * @return a Position, the opposite of that passed in
     */
    private Position switchPos(Position old){
        if(old == Position.WEST)
            return Position.EAST;
        else
            return Position.WEST;
    }
    /**
     * Method to check to see if the incoming variable is on the same side of
     * a bridge as the flashlight
     * @param Pos the position being checked against flashlight Position
     * @param state the state to check flashlight position location
     * @return a boolean value depending on if evals to true/false
     */
    private boolean checkVsFlash(Position Pos, BridgeState state){
        if(Pos == state.getFlashlightPosition() )
            return true;
        else
            return false;
    }
}

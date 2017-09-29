package waterjug;

import framework.Move;
import framework.State;

/**
 * This class represents moves in the Water Jug problem.
 * A move object stores its move name and knows how to apply itself
 * to a water jug state to create a new state representing the result
 * of the move.
 * Note that this class extends the abstract class <b>Move</b> and
 * therefore imports <b>framework.Move</b>.
 * This class inherits the <b>getMoveName()</b> method from its parent
 * and thus it should not have an instance field for the move name.
 * @author your name here
*/
public class WaterJugMove extends Move {

    /**
     * Constructs a new water jug move object.
     * Note that the move name is passed to the parent constructor
     * using <b>super</b>.
     * @param moveName the name of this move.
     * It is an error if the name is not one of the following:
     * <ul>
     * <li> "Fill Jug X" </li>
     * <li> "Fill Jug Y" </li>
     * <li> "Empty Jug X" </li>
     * <li> "Empty Jug Y" </li>
     * <li> "Transfer Jug X to Jug Y" </li>
     * <li> "Transfer Jug Y to Jug X" </li>
     * </ul>
     */
    public WaterJugMove(String moveName) {
	super(moveName);
        moveType = moveName;
    }

    /**
     * Attempts to perform this move on a given water jug state.
     * Note that this method implements the abstract <b>doMove</b> method declared
     * in the parent.
     * Thus the argument of type <b>State</b> must be cast to type
     * <b>WaterJugState</b> before processing.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new water jug state object is returned that
     * reflects this move.
     * A move cannot be performed if trying to fill or transfer to an already
     * full jug, or if trying to empty or transfer from an empty jug.
     * If the move cannot be performed <b>null</b> is returned.
     * @param otherState the water jug state on which this move is to be performed
     * @return a new water jug state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public State doMove(State otherState) {
	WaterJugState state = (WaterJugState) otherState;
        
        if(moveType.equals("Fill Jug X"))
            return new WaterJugState(3, state.getJugAmount(1));
        if(moveType.equals("Fill Jug Y"))
            return new WaterJugState(state.getJugAmount(0),4);
        if(moveType.equals("Empty Jug X"))
            return new WaterJugState(0,state.getJugAmount(1));
        if(moveType.equals("Empty Jug Y"))
            return new WaterJugState(state.getJugAmount(0),0);
        if(moveType.equals("Transfer Jug X to Jug Y"))
        {
            int newX = state.getJugAmount(0);
            int newY = state.getJugAmount(1);
            
            while(newY < 4 && newX > 0)
            {
                newY++;
                newX--;
            }
            return new WaterJugState(newX, newY);
        }
        
        if(moveType.equals("Transfer Jug Y to Jug X"))
        {
            int newX = state.getJugAmount(0);
            int newY = state.getJugAmount(1);
            
            while(newX < 3 && newY > 0)
            {
                newY--;
                newX++;
            }
            return new WaterJugState(newX,newY);
        }
        else
            return null;
    }
    
    // Private methods and instance fields should go here
    private final String moveType;
}

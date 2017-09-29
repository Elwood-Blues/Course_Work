package bridge;

/**
 * This class represents moves in the Bridge Crossing problem.
 * A move object stores its move name and knows how to apply itself
 * to a bridge state to create a new state representing the result
 * of the move.
 * @author Peter Braband #3418868
 */
public class BridgeMove {
    /**
     * Holds a string containing one of the following items depending on which
     * move it is.
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
    String moveType;
    /**
     * Constructs a new bridge move object.
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
        moveType = moveName;
    }

    /**
     * Getter (accessor) for this move object's move name.
     * @return this move object's move name
     */
    public String getMoveName() {
        return moveType;
    }
    
    /**
     * Attempts to perform this move on a given bridge state.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new bridge state object is returned that
     * reflects this move.
     * A move cannot be performed if the flashlight is not on the same side
     * as the crossing person(s), or if a pair of persons who are crossing are not
     * on the same side.
     * If the move cannot be performed <b>null</b> is returned.
     * @param state the bridge state on which this move is to be performed
     * @return a new bridge state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public BridgeState doMove(BridgeState state) {
        Position newP1 = state.getP1Position();
        Position newP2 = state.getP2Position();
        Position newP5 = state.getP5Position();
        Position newP10 = state.getP10Position();
        Position newFlash = state.getFlashlightPosition();
        int timeToAdd = 0;
        
        if (moveType.contains("P1 ")) {
            if (CheckP1(state)) {
                newP1 = switchPos(newP1);
                timeToAdd = 1;
            }
            else return null;
        }
        if (moveType.contains("P2 ") || moveType.contains(" P2")) {
            if (CheckP2(state)) {
                newP2 = switchPos(newP2);
                timeToAdd = 2;
            }
            else return null;
        }
        if (moveType.contains("P5 ") || moveType.contains(" P5")) {
            if (CheckP5(state)) {
                newP5 = switchPos(newP5);
                timeToAdd = 5;
            }
            else return null;
        }
        if (moveType.contains("P10 ") || moveType.contains(" P10")) {
            if (CheckP10(state)) {
                newP10 = switchPos(newP10);
                timeToAdd = 10;
            }
            else return null;
        }
        
        newFlash = switchPos(newFlash);
        BridgeState newState = new BridgeState(newP1, newP2, newFlash, newP5,
                    newP10, state.getTimeSoFar() + timeToAdd);
        
        return newState;
    }
    
    /**
     * Checks to see if P1 is on the same side as flashlight.
     * Performs a comparison between the position of P1 and the flashlight.
     * @param state the bridge state to check.
     * @return a boolean 
     */
    private boolean CheckP1(BridgeState state) {
        if (state.getP1Position() == state.getFlashlightPosition()) return true;
        else return false;
    }
    
     /**
     * Checks to see if P2 is on the same side as flashlight.
     * Performs a comparison between the position of P2 and the flashlight.
     * @param state the bridge state to check.
     * @return a boolean 
     */
    private boolean CheckP2(BridgeState state) {
        if (state.getP2Position() == state.getFlashlightPosition()) return true;
        else return false;
    }
    
     /**
     * Checks to see if P5 is on the same side as flashlight.
     * Performs a comparison between the position of P5 and the flashlight.
     * @param state the bridge state to check.
     * @return a boolean 
     */
    private boolean CheckP5(BridgeState state) {
        if (state.getP5Position() == state.getFlashlightPosition()) return true;
        else return false;
    }
    
     /**
     * Checks to see if P10 is on the same side as flashlight.
     * Performs a comparison between the position of P10 and the flashlight.
     * @param state the bridge state to check.
     * @return a boolean 
     */
    private boolean CheckP10(BridgeState state) {
        if (state.getP10Position() == state.getFlashlightPosition())
            return true;
        else return false;
    }
    
     /**
     * Returns the opposite position.
     * Checks the position passed in and returns the opposite position.  
     * Used to swap positions.
     * @param oldPosition the old position to check
     * @return a Position, opposite of the one passed in.
     */
    private Position switchPos(Position oldPosition) {
        if (oldPosition == Position.WEST) return Position.EAST;
        else return Position.WEST;
    }
}
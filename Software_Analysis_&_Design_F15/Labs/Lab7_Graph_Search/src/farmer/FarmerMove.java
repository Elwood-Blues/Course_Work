package farmer;

import framework.Move;
import framework.State;
import java.util.Arrays;
import java.util.List;

/**
   This class is responsible for performing the legal moves that can
   be done on states in the farmer, wolf, goat, and cabbage (FWGC) problem.
 */
public class FarmerMove extends Move {

    /**
       Creates a FWGC problem move, storing the move name.
       @param moveName a user command, one of TAKE_SELF,
              TAKE_WOLF, TAKE_GOAT, or TAKE_CABBAGE.
     */
    public FarmerMove(String moveName) {
	super(moveName);
        if (!moveNames.contains(moveName)) {
            throw new RuntimeException("Bad move name: " + moveName);
        }
    }

    /**
     * Performs this FWGC move on a given state.  Depending on the
     * move name for this state, this method attempts to create a new state
     * that is the result of applying the move to the given state.  If the
     * move cannot be performed because it is not possible or it results in
     * an unsafe state, <b>null</b> is returned.  Otherwise the resulting 
     * NEW state is returned.  Note that the argument state is not altered.
     * @param s an existing FWGC state
     * @return a new state resulting from the move (might be null)
     */
    public State doMove(State s) {
	FarmerState state = (FarmerState) s;
        FarmerState next;
        if (getMoveName().equals(TAKE_SELF))
            next = tryFarmer(state);
        else if (getMoveName().equals(TAKE_WOLF))
            next = tryWolf(state);
        else if (getMoveName().equals(TAKE_GOAT))
            next = tryGoat(state);
        else // must be TAKE_CABBAGE
            next = tryCabbage(state);
        if ( next == null || !isSafe(next) )
            return null;
        return next;
    }

    /**
     * Utility method to find the opposite side of a character.
     * @param side the side whose opposite is needed
     * @return the opposite side of the given side
     */
    private static FarmerState.Side opposite(FarmerState.Side side) {
	if ( side == FarmerState.Side.EAST )
	    return FarmerState.Side.WEST;
	else
	    return FarmerState.Side.EAST;
    }

    /**
     * Utility method to determine whether a FWGC state is safe.
     * A state is not safe if the farmer leaves the wolf with the goat or
     * the goat with the cabbage.
     * @param state the state to be tested for safety
     * @return whether the state is safe
     */
    private static boolean isSafe(FarmerState state) {

	FarmerState.Side farmer = state.getFarmer();
	FarmerState.Side wolf = state.getWolf();
	FarmerState.Side goat = state.getGoat();
	FarmerState.Side cabbage = state.getCabbage();

	return !((goat == wolf) && (goat != farmer) ||
		 (cabbage == goat) && (cabbage != farmer));
    }

    /**
     * Creates a new FWGC state by crossing farmer alone.
     * The given state is not changed.
     * @param state the given state
     * @return the new state or null if it is not safe
     */
    private FarmerState tryFarmer(FarmerState state) {
	return new FarmerState(opposite(state.getFarmer()),
					   state.getWolf(),
					   state.getGoat(),
					   state.getCabbage());
    }

    /**
     * Tries to create a new FWGC state by crossing the farmer and the wolf.
     * The given state is not changed.
     * @param state the given state
     * @return the new state or null if the farmer and wolf are
     * not on the same side or the new state is not safe
     */
    private FarmerState tryWolf(FarmerState state) {
	if ( state.getFarmer() != state.getWolf() )
	     return null;
	return new FarmerState(opposite(state.getFarmer()),
					   opposite(state.getWolf()),
					   state.getGoat(),
					   state.getCabbage());
    }

    /**
     * Tries to create a new FWGC state by crossing the farmer and the goat.
     * The given state is not changed.
     * @param state the given state
     * @return the new state or null if the farmer and goat are
     * not on the same side or the new state is not safe
     */
    private FarmerState tryGoat(FarmerState state) {
	if ( state.getFarmer() != state.getGoat() )
	     return null;
	return new FarmerState(opposite(state.getFarmer()),
					   state.getWolf(),
					   opposite(state.getGoat()),
					   state.getCabbage());
    }

    /**
     * Tries to create a new FWGC state by crossing the farmer and the cabbage.
     * The given state is not changed.
     * @param state the given state
     * @return the new state or null if the farmer and cabbage are
     * not on the same side or the new state is not safe
     */
    private FarmerState tryCabbage(FarmerState state) {
	if ( state.getFarmer() != state.getCabbage() )
	     return null;
	return new FarmerState(opposite(state.getFarmer()),
					   state.getWolf(),
					   state.getGoat(),
					   opposite(state.getCabbage()));
    }
/**
       Name of move to cross farmer alone
     */
    private static final String TAKE_SELF = "Farmer takes self";

    /**
       Name of move to cross farmer with wolf
     */
    private static final String TAKE_WOLF = "Farmer takes wolf";

    /**
       Name of move to cross farmer with goat
     */
    private static final String TAKE_GOAT = "Farmer takes goat";

    /**
       Name of move to cross farmer with cabbage
     */
    private static final String TAKE_CABBAGE = "Farmer takes cabbage";
    
    private static final List<String> moveNames = Arrays.asList(new String[]{TAKE_SELF, TAKE_WOLF, TAKE_GOAT, TAKE_CABBAGE});
}

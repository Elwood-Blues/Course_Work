package farmer;

import framework.Move;
import framework.Problem;
import java.util.ArrayList;
import java.util.List;

/**
   This class keeps track of the current state of the farmer, wolf, goat,
   and cabbage (FWGC) problem, attempts to transform the current 
   state given a user command, and checks for problem success.
   It also manages an introductory description of the problem.
*/
public class FarmerProblem extends Problem {

    /**
     * The FWGC problem constructor stores information about the problem,
     * including the initial state, the final state for success checking, and
     * an explanatory introduction string.
     */
    public FarmerProblem() {
        
        setCurrentState(new FarmerState(FarmerState.Side.WEST,  // F |  |
                                        FarmerState.Side.WEST,  // W |  |
                                        FarmerState.Side.WEST,  // G |  |
                                        FarmerState.Side.WEST));// C |  |
                
        setIntroduction("Welcome to the Farmer, Wolf, Goat, and Cabbage Problem\n\n"
                      + "A farmer and his wolf, goat, and cabbage come to the edge of\n"
                      + "a river they wish to cross.  There is a boat at the river's edge\n"
                      + "that only the farmer can row.  The farmer can take at most one\n"
                      + "other object besides himself on a crossing, but if the wolf is\n"
                      + "ever left with the goat, the wolf will eat the goat; similarly,\n"
                      + "if the goat is left with the cabbage, the goat will eat the\n"
                      + "cabbage.  Devise a sequence of crossings of the river so that\n"
                      + "all four characters arrive safely on the other side.\n");

        List<Move> moves = new ArrayList<Move>();

        moves.add(new FarmerMove("Farmer takes self"));
        moves.add(new FarmerMove("Farmer takes wolf"));
        moves.add(new FarmerMove("Farmer takes goat"));
        moves.add(new FarmerMove("Farmer takes cabbage"));

        setMoves(moves);
    }
    
    /**
     * Tests for whether the current state of the problem is a success.
     * @return true if all characters are on the east side of the river
     */
    public boolean success() {
        FarmerState current = (FarmerState) getCurrentState();
        return current.getCabbage() == FarmerState.Side.EAST &&
               current.getGoat() == FarmerState.Side.EAST &&
               current.getWolf() == FarmerState.Side.EAST &&
               current.getFarmer() == FarmerState.Side.EAST;
    }

}

package bridge;

/**
 * This class represents states of the Bridge Crossing problem.
 * It creates new bridge states, tests states for equality,
 * and produces string representations of them.
 * @author Peter Braband
 */
public class BridgeState {
    /**
     * Holds the current time elapsed.  Needed for multiple methods.
     */
    private final int time;
    /**
     * Contains the position of each person.  P1 = index 0, P2 = index 1, 
     * flashlight = index 2, P5 = index 3, P10 = index 4.
     */
    private final Position[] arrayPos = new Position[5];
    
    /**
     * An array of strings representing the text to display when the game board
     * is generated.
     */
    private final String[] stringPos = {"P1","P2","f", 
        "P5", "P10"};
    
    /**
     * Creates a new bridge state.  
     * Besides storing the positions of the persons and flashlight, a
     * bridge state should also store the time taken so far to cross in
     * integer minutes.
     * @param p1Position position of the person who can cross in 1 minute
     * @param p2Position position of the person who can cross in 2 minutes
     * @param flashlightPosition position of the flashlight
     * @param p5Position position of the person who can cross in 5 minutes
     * @param p10Position  position of the person who can cross in 10 minutes
     * @param timeSoFar time taken so far
     */
    public BridgeState(Position p1Position, 
                       Position p2Position, 
                       Position flashlightPosition, 
                       Position p5Position, 
                       Position p10Position,
                       int timeSoFar) {
        arrayPos[0] = p1Position;
        arrayPos[1] = p2Position;
        arrayPos[3] = p5Position;
        arrayPos[4] = p10Position;
        arrayPos[2] = flashlightPosition;
        time = timeSoFar;
    }
    
    /**
     * Tests this bridge state with another for equality.
     * Two bridge states are equal if the positions of the persons and 
     * flashlight in one state are matched by their positions in the other.
     * Note that the time taken to cross so far is not taken into account
     * when considering equality.
     * @param other the other bridge state to be tested against this one.
     * @return true if this state is equal to the other state, false otherwise
     */
    public boolean equals(BridgeState other) {
        if (arrayPos[0] == other.getP1Position() && 
                arrayPos[1] == other.getP2Position() && 
                arrayPos[3] == other.getP5Position() && 
                arrayPos[4] == other.getP10Position() &&
                arrayPos[2] == other.getFlashlightPosition())
        {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Creates a string representation of this state for display to the user
     * trying to solve the problem.
     * Note that the time so far to cross is part of the string representation.
     * @return the string representation of this state
     */
    public String toString() {
        java.lang.StringBuilder statement = new java.lang.StringBuilder();
        
        for (int i = 0; i < 5; i++)
        {
            if(i != 2)
            {
                if (arrayPos[i] == Position.WEST)
                {
                    if (i != 4)
                    {
                        statement.append(" ");
                    }
                    statement.append(stringPos[i] + " |   |\n");
                }
                else
                {
                    statement.append("    |   | " + stringPos[i] + "\n");
                }
            }
            else
            {
                if (arrayPos[i] == Position.WEST)
                {
                    statement.append("  f |===|\n");
                }
                else
                {
                    statement.append("    |===| f\n");
                }
            }
        }
        
        statement.append("Time elapsed so far: " + time + " minutes.\n");
	return statement.toString();
    }
    /**
     * Gets the position (east or west) of the Flashlight.
     * @return the current position.
     */
    public Position getFlashlightPosition() {
        return arrayPos[2];
    }
    /**
     * Gets the position (east or west) of the Person 10.
     * @return the current position.
     */
    public Position getP10Position() {
        return arrayPos[4];
    }
    /**
     * Gets the position (east or west) of the Person 1.
     * @return the current position.
     */
    public Position getP1Position() {
        return arrayPos[0];
    }
    /**
     * Gets the position (east or west) of the Person 2.
     * @return the current position.
     */
    public Position getP2Position() {
        return arrayPos[1];
    }
    /**
     * Gets the position (east or west) of the Person 5.
     * @return the current position.
     */
    public Position getP5Position() {
        return arrayPos[3];
    }
    /**
     * Gets the current time elapsed in the state so far.
     * @return an integer with the current time elapsed.
     */
    public int getTimeSoFar() {
        return time;
    }
    
}

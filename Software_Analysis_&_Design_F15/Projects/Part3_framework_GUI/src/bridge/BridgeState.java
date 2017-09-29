package bridge;

import framework.State;

/**
 * This class represents states of the Bridge Crossing problem.
 * It creates new bridge states, tests states for equality,
 * and produces string representations of them.
 * Note that this class implements the <b>State</b> interface
 * and therefore imports <b>framework.State</b>.
 * Except for the import and the class header, this class can be
 * the same as in the previous assignment.
 * @author Peter Braband
 */
public class BridgeState implements State {

    /**
     * Creates a new bridge state.  
     * Besides storing the positions of the persons and flashlight, a
     * bridge state should also store the time taken to get to this state in
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
        arrayPos[2] = flashlightPosition;
        arrayPos[3] = p5Position;
        arrayPos[4] = p10Position;
        time = timeSoFar;
    }
    
    /**
     * Compares this bridge state with another for equality.
     * Note that this method overrides the <b>equals</b> method defined
     * in <b>java.lang.Object</b>.
     * Thus the argument of type <b>Object</b> must be cast to type
     * <b>BridgeState</b> before processing.
     * Two bridge states are equal if the positions of the persons and 
     * flashlight in one state are matched by their positions in the other.
     * Note that the time taken to cross so far is not taken into account
     * when considering equality.
     * @param other the other bridge state to be compared with this one.
     * @return whether this state is equal to the other state
     */
    public boolean equals(Object other) {
        BridgeState state = (BridgeState) other;
        if(arrayPos[0] == state.getP1Position() &&
                arrayPos[1] == state.getP2Position() &&
                arrayPos[2] == state.getFlashlightPosition() &&
                arrayPos[3] == state.getP5Position() &&
                arrayPos[4] == state.getP10Position() )
            return true;
        else
            return false;
        
    }
    
    /**
     * Creates a string representation of this state for display to the user
     * trying to solve the problem.
     * Note that the time so far to cross is part of the string representation.
     * @return the string representation of this state
     */
    public String toString() {
        StringBuilder  state = new StringBuilder(); 
                
        if(this.getP1Position() == Position.EAST){
            state.append("    |   | P1\n");
        }
        else {
            state.append(" P1 |   |\n");
        }
        if(this.getP2Position() == Position.EAST){
            state.append("    |   | P2\n");
        }
        else{
            state.append(" P2 |   |\n");
        }
        if(this.getFlashlightPosition() == Position.EAST){
            state.append("    |===| f\n");
        }
        else{
            state.append("  f |===|\n");
        }        
        if(this.getP5Position() == Position.EAST){
            state.append("    |   | P5\n");
        }
        else{
            state.append(" P5 |   |\n");
        }          
        if(this.getP10Position() == Position.EAST){
            state.append("    |   | P10\n");
        }
        else{
            state.append("P10 |   |\n");
        }   
        
        state.append("Time elapsed so far: " + this.getTimeSoFar() + " minutes.\n");
        
        
     
    return state.toString();  
    }

    /**
     * Getter (accessor) for the position of the flashlight in this state.
     * @return the position of the flashlight
     */
    public Position getFlashlightPosition() {
        return arrayPos[2]; // You must provide
    }

    /**
     * Getter (accessor) for the position of person P1 in this state.
     * @return the position of person P1
     */
    public Position getP1Position() {
        return arrayPos[0]; // You must provide
    }

    /**
     * Getter (accessor) for the position of person P2 in this state.
     * @return the position of person P2
     */
    public Position getP2Position() {
        return arrayPos[1]; // You must provide
    }

    /**
     * Getter (accessor) for the position of person P5 in this state.
     * @return the position of person P5
     */
    public Position getP5Position() {
        return arrayPos[3]; // You must provide
    }

    /**
     * Getter (accessor) for the position of person P10 in this state.
     * @return the position of person P10
     */
    public Position getP10Position() {
        return arrayPos[4]; // You must provide
    }

    /**
     * Getter (accessor) for the time taken to get to this state.
     * @return the time taken to get to this state
     */
    public int getTimeSoFar() {
        return time; // You must provide
    }
    
    // Private methods and instance fields should go here
    private final int time;
    private final Position[] arrayPos = new Position[5];
    private final String[] stringPos = {"P1", "P2", "f", "P5", "P10"};
}

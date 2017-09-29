package waterjug;

import framework.State;

/**
 * This class represents states of the Water Jug problem.
 * It creates new water jug states, tests states for equality,
 * and produces string representations of them.
 * Note that this class implements the <b>State</b> interface
 * and therefore imports <b>framework.State</b>.
 */
public class WaterJugState implements State {

    public WaterJugState(int jug1, int jug2){
        jug[0] = jug1;
        jug[1] = jug2;
    }

    /**
       Tests for equality between this state and the argument state.
       Two states are equal if the X jugs have the same amount of water
       and the Y jugs have the same amount of water.
       @param other the state to test against this state
       @return whether the states are equal
    */
    public boolean equals(Object other) {
	WaterJugState otherJug = (WaterJugState) other;
        if(otherJug.getJugAmount(0) == jug[0] &&
                otherJug.getJugAmount(1) == jug[1])
            return true;
        else 
            return false;
    }

    /**
       Creates a primitive, non-GUI representation of this WaterJugState object.
       @return the string representation of this water jug state
     */
    public String toString() {
	StringBuilder statement = new StringBuilder();
        for (int i = 6; i > 0; --i)
        {
            if(i == 1)
            {
                statement.append("  X      Y  \n");
            }
            else if(i == 2)
            {
                statement.append("+---+  +---+\n");
            }
            else if(i == 6)
            {
                statement.append("       |");
                
                if(jug[1] == 4) statement.append("***");
                else statement.append("   ");
                    
                statement.append("|\n");
            }
            else
            {                
                if(jug[0] >= i-2) statement.append("|***");
                else statement.append("|   ");
                
                statement.append("|  |");
                
                if(jug[1] >= i-2) statement.append("***");
                else statement.append("   ");
                
                statement.append("|\n");                
            }
            
        }
        
	return statement.toString();
    }
    
    // Private methods and instance fields should go here
private final int JUG1MAX = 3; // Jug1's max capacity
private final int JUG2MAX = 4; //Jug2's max capacity

/**
 * Array to hold the height of jug1 and jug2
 */
private int[] jug = new int[2];

/**
 * Getter Method for amount in a jug
 * @param i index for the jug to return
 * @return the amount currently in the jug
 */
public int getJugAmount(int i){
    return jug[i];
}
}

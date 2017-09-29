package farmer;

import framework.State;
import graph.SimpleVertex;

/**
   This class is responsible for representing the internal details of a
   farmer, wolf, goat, and cabbage (FWGC) state, creating a displayable
   non-GUI representation, and checking for state equality.
 */
public class FarmerState extends SimpleVertex implements State {

    /**
       An enumerated type to represent sides of the river.  This is not
       necessary, but an optional approach to internal representation.
       It can be skipped in favor of simply storing the strings "west"
       or "east" to represent a character's side of the river.
     */
    public enum Side {WEST, EAST};

    /**
       The side the farmer is on
     */
    private Side farmer;

    /**
       The side the wolf is on
     */
    private Side wolf;

    /**
       The side the goat is on
     */
    private Side goat;

    /**
       The side the cabbage is on
     */
    private Side cabbage;

    /**
       Creates a new FWGC state.
       @param farmer the side of the farmer
       @param wolf the side of the wolf
       @param goat the side of the goat
       @param cabbage the side of the cabbage
    */
    public FarmerState(Side farmer, Side wolf, Side goat, Side cabbage) {
	this.farmer = farmer;
	this.wolf = wolf;
	this.goat = goat;
	this.cabbage = cabbage;
    }

    /**
       Creates a new FWGC state, storing each character's side of the river.
       @param farmer the side of the farmer as a string
       @param wolf the side of the wolf as a string
       @param goat the side of the goat as a string
       @param cabbage the side of the cabbage as a string
    */
    public FarmerState(String farmer, String wolf, String goat, String cabbage) {
	this.farmer = toSide(farmer);
	this.wolf = toSide(wolf);
	this.goat = toSide(goat);
	this.cabbage = toSide(cabbage);
    }

    private Side toSide(String s) {

	if ( s.equals("west") )
	    return Side.WEST;
	else // s must be "east"
	    return Side.EAST;

    }

    /**
       Accessor for farmer side.
       @return the side of the farmer
     */
    public Side getFarmer() {
	return farmer;
    }

    /**
       Accessor for wolf side.
       @return the side of the wolf
     */
    public Side getWolf() {
	return wolf;
    }

    /**
       Accessor for goat side.
       @return the side of the goat
     */
    public Side getGoat() {
	return goat;
    }

    /**
       Accessor for cabbage side.
       @return the side of the cabbage
     */
    public Side getCabbage() {
	return cabbage;
    }

    /**
       Tests for equality between this state and the argument state.
       Two states are equal if each character is on the same side
       as its counterpart in the other state.  Note that the argument
       <b>other</b> must be cast to the <b>FarmerState</b> type before
       using it.
       @param other the other state to test against this state
       @return whether the states are equal
    */
    public boolean equals(Object other) {
	if (this == other) return true;
	if (other == null) return false;
	if (getClass() != other.getClass()) return false;
	FarmerState state = (FarmerState) other;
	return (this.farmer == state.farmer &&
		this.wolf == state.wolf &&
		this.goat == state.goat &&
		this.cabbage == state.cabbage);
    }

    /**
       Makes, but does not display, a non-GUI representation of this FWGC state.
       @return the string representation of this FWGC state
     */
    public String toString() {

	StringBuilder buf = new StringBuilder();

	String fWest = " F |  |   \n";
	String fEast = "   |  | F \n";
	String wWest = " W |  |   \n";
	String wEast = "   |  | W \n";
	String gWest = " G |  |   \n";
	String gEast = "   |  | G \n";
	String cWest = " C |  |   \n";
	String cEast = "   |  | C \n";

	if (farmer == Side.WEST)
	    buf.append(fWest);
	else
	    buf.append(fEast);

	if (wolf == Side.WEST)
	    buf.append(wWest);
	else
	    buf.append(wEast);

	if (goat == Side.WEST)
	    buf.append(gWest);
	else
	    buf.append(gEast);

	if (cabbage == Side.WEST)
	    buf.append(cWest);
	else
	    buf.append(cEast);

	return buf.toString();
    }
}

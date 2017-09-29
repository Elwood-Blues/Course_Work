function FarmerProblem() {
    this.name = "Farmer, Wolf, Goat, and Cabbage";

    this.introduction = 
        "A farmer and his wolf, goat, and cabbage come to the edge of " +
        "a river they wish to cross.  There is a boat at the river's edge " +
        "that only the farmer can row.  The farmer can take at most one " +
        "other object besides himself on a crossing, but if the wolf is " +
        "ever left with the goat, the wolf will eat the goat; similarly, " +
        "if the goat is left with the cabbage, the goat will eat the " +
        "cabbage.  Devise a sequence of crossings of the river so that " +
        "all four characters arrive safely on the other side.";
    this.initialState = new FarmerState(WEST, WEST, WEST, WEST);

    this.currentState = this.initialState;

    this.finalState = new FarmerState(EAST, EAST, EAST, EAST);

    var moveNames = [GO_ALONE, TAKE_WOLF, TAKE_GOAT, TAKE_CABBAGE];

    this.moves = moveNames.map(function(name) { return new FarmerMove(name); });

    this.success = function () {
	return this.currentState.equals(this.finalState);
    };
}

function BridgeProblem() {
    this.name = "Bridge Problem";

    this.introduction = 
        "Get your people across the bridge in 17 minutes.  Take a flashlight "+
        "with you.";
    this.initialState = new BridgeState(WEST, WEST, WEST, WEST, WEST, 0);

    this.currentState = this.initialState;

    this.finalState = new BridgeState(EAST, EAST, EAST, EAST, EAST, 17);

    var moveNames = [P1, P2, P5, P10, P1P2, P1P5, P1P10, P2P5, P2P10, P5P10];

    this.moves = moveNames.map(function(name) { return new BridgeMove(name); });

    this.success = function () {
        if (true) {
            return this.currentState.equals(this.finalState);
        }
        else {
            return false;
        }
    };
}
// You must provide
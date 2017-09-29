var FILL_X =  "Fill Jug X";   // move names
var FILL_Y =  "Fill Jug Y";
var EMPTY_X = "Empty Jug X";
var EMPTY_Y = "Empty Jug Y";
var X_TO_Y =  "Transfer Jug X to Jug Y";
var Y_TO_X =  "Transfer Jug Y to Jug X";

function WaterJugMove(name) {
    this.name = name;

    this.doMove = function (state) {  // Attempts to perform this move on
	var x = state.x;              // a given state.
	var y = state.y;              // Returns a new state or null.
	switch (this.name) {
	case FILL_X: return x === 3 ? null : new WaterJugState(3, y);
	case FILL_Y: return y === 4 ? null : new WaterJugState(x, 4);
	case EMPTY_X: return x === 0 ? null : new WaterJugState(0, y);
	case EMPTY_Y: return y === 0 ? null : new WaterJugState(x, 0);
	case X_TO_Y: return x === 0 || y === 4 ? null : transfer(4, x, y);
	case Y_TO_X: return x === 3 || y === 0 ? null : transfer(3, y, x);
	default: alert("Error in doMove for WaterJug"); // Shouldn't happen!
	}
    };
}

function transfer(limit, source, dest) { // creates a new state with water
    var new_source;                      // transferred from source to dest
    var new_dest;
    var available = limit - dest;
    if (source <= available) {
	new_dest = dest + source;
	new_source = 0;
    }
    else {
	new_dest = dest + available;
	new_source = source - available;
    }
    if (limit === 4) // destination is jug Y
	return new WaterJugState(new_source, new_dest);
    else // limit == 3 destination is jug X
	return new WaterJugState(new_dest, new_source);
}
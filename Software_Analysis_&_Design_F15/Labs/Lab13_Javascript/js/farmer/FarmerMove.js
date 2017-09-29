var GO_ALONE =     "Farmer Goes Alone";   // move names
var TAKE_WOLF =    "Farmer Takes Wolf";
var TAKE_GOAT =    "Farmer Takes Goat";
var TAKE_CABBAGE = "Farmer Takes Cabbage";

function FarmerMove(name) {
    this.name = name;

    this.doMove = function (state) {  // Attempts to perform this move on
	var next;                     // a given state.
	switch (this.name) {          // Returns a new state or null.
        case GO_ALONE: next = moveF(state); break;
        case TAKE_WOLF: next = moveFW(state); break;
        case TAKE_GOAT: next = moveFG(state); break;
        case TAKE_CABBAGE: next = moveFC(state); break;
        default: alert("Error in doMove for Farmer"); // Shouldn't happen!
	}
	if (next === null || !next.safe())
	    return null;
	return next;
    };
}

function moveF(state) {  // creates a new state with farmer moved
    return new FarmerState(opposite(state.f), 
			   state.w, 
			   state.g, 
			   state.c);
}

function moveFW(state) {  // creates a new state with farmer and wolf moved
    return state.f !== state.w ? null : new FarmerState(opposite(state.f), 
						       opposite(state.w), 
						       state.g, 
						       state.c);
}

function moveFG(state) {  // creates a new state with farmer and goat moved
    return state.f !== state.g ? null : new FarmerState(opposite(state.f), 
						       state.w, 
						       opposite(state.g), 
						       state.c);
}

function moveFC(state) {  // creates a new state with farmer and cabbage moved
    return state.f !== state.c ? null : new FarmerState(opposite(state.f), 
						       state.w, 
						       state.g, 
						       opposite(state.c));
}

function opposite(side) {
    if ( side === WEST )
        return EAST;
    else
        return WEST;
}

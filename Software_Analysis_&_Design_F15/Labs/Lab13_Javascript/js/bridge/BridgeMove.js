var P1 = "P1 crosses alone";
var P2 = "P2 crosses alone";
var P5 = "P5 crosses alone";
var P10 = "P10 crosses alone";
var P1P2 = "P1 crosses with P2";
var P1P5 = "P1 crosses with P5";
var P1P10 = "P1 crosses with P10";
var P2P5 = "P2 crosses with P5";
var P2P10 = "P2 crosses with P10";
var P5P10 = "P5 crosses with P10";

function BridgeMove(name) {
    this.name = name;

    this.doMove = function (state) {  // Attempts to perform this move on
	var next;   
	switch (this.name) {          // Returns a new state or null.
        case P1: next = moveP1(state); break;
        case P2: next = moveP2(state); break;
        case P5: next = moveP5(state); break;
        case P10: next = moveP10(state); break;
        case P1P2: next = moveP1P2(state); break;
        case P1P5: next = moveP1P5(state); break;
        case P1P10: next = moveP1P10(state); break;
        case P2P5: next = moveP2P5(state); break;
        case P2P10: next = moveP2P10(state); break;
        case P5P10: next = moveP5P10(state); break;
        default: alert("Error in doMove for Bridge"); // Shouldn't happen!
	}
	if (next === null)
	    return null;
	return next;
    };
}

function moveP1(state) {
    
    return state.flash !== state.p1 ? null : new BridgeState(opposite(state.p1), 
			   state.p2,
                           opposite(state.flash),
			   state.p5, 
			   state.p10,
                           state.time + 1);
    
}

function moveP2(state) {
    return state.flash !== state.p2 ? null : new BridgeState(state.p1, 
			   opposite(state.p2),
                           opposite(state.flash),
			   state.p5, 
			   state.p10,
                           state.time + 2);
}

function moveP5(state) {
    return state.flash !== state.p5 ? null : new BridgeState(state.p1, 
			   state.p2,
                           opposite(state.flash),
			   opposite(state.p5), 
			   state.p10,
                           state.time + 5);
}

function moveP10(state) {  
    return state.flash !== state.p10 ? null : new BridgeState(state.p1, 
			   state.p2,
                           opposite(state.flash),
			   state.p5, 
			   opposite(state.p10),
                           state.time + 10);
}

function moveP1P2(state) { 

    if (state.p1 !== state.p2) {     
        return null;
    }

    if (state.p1 !== state.flash) {
        return null;
    }

    return new BridgeState(opposite(state.p1), 
        opposite(state.p2), 
        opposite(state.flash),
        state.p5,
        state.p10,
        state.time + 2);
}

function moveP1P5(state) {  
    if(state.p1 !== state.p5) {
        return null;
    }
    
    if(state.p1 !== state.flash) {
        return null;
    }
    
    return new BridgeState(opposite(state.p1), 
        state.p2,
        opposite(state.flash),
        opposite(state.p5), 
        state.p10,
        state.time + 5);
}

function moveP1P10(state) {  
    if(state.p1 !== state.p10) {
        return null;
    }
    
    if(state.p1 !== state.flash) {
        return null;
    }

    return new BridgeState(opposite(state.p1), 
        state.p2,
        opposite(state.flash),
        state.p5, 
        opposite(state.p10),
        state.time + 10);
}

function moveP2P5(state) {  
    if(state.p2 !== state.p5) {
        return null;
    }
    
    if(state.p2 !== state.flash) {
        return null;
    }
    
    return new BridgeState (state.p1, 
        opposite(state.p2),
        opposite(state.flash),
        opposite(state.p5), 
        state.p10,
        state.time + 5);
}

function moveP2P10(state) {  
    if(state.p2 !== state.p10) {
        return null;
    }
    
    if(state.p2 !== state.flash) {
        return null;
    }
    
    return new BridgeState (state.p1, 
        opposite(state.p2),
        opposite(state.flash),
        state.p5, 
        opposite(state.p10),
        state.time + 10);
}

function moveP5P10(state) {  
    if(state.p5 !== state.p10) {
        return null;
    }
    
    if(state.p5 !== state.flash) {
        return null;
    }
    
    return new BridgeState (state.p1, 
        state.p2,
        opposite(state.flash),
        opposite(state.p5), 
        opposite(state.p10),
        state.time + 10);
}

function opposite(side) {
    if ( side === WEST )
        return EAST;
    else
        return WEST;
}
// You must provide
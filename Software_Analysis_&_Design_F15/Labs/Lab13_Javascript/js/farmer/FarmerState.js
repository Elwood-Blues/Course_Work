var WEST = 0;
var EAST = 1;

function FarmerState(f, w, g, c) {
    this.f = f;  // farmer side
    this.w = w;  // wolf side
    this.g = g;  // goat side
    this.c = c;  // cabbage side

    this.safe = function () { // returns false if goat and wolf are alone
	                      // or cabbage and goat are alone
	return !((this.g === this.w) && (this.g !== this.f) 
		 ||
		 (this.c === this.g) && (this.c !== this.f));
    };

    this.equals = function (other) {      // returns true if other state
	if (other === null) return false;  // has characters on same sides
	return this.f === other.f &&
	this.w === other.w &&
	this.g === other.g &&
	this.c === other.c;
    };

    this.width = 10;  // used to allocate space
    this.height = 6;  // for state display

    this.toString = function () {  // builds and returns a string
                                   // representing this state
	var fWest = " F |  |   \n";
	var fEast = "   |  | F \n";
	var wWest = " W |  |   \n";
	var wEast = "   |  | W \n";
	var gWest = " G |  |   \n";
	var gEast = "   |  | G \n";
	var cWest = " C |  |   \n";
	var cEast = "   |  | C \n";
    
	var buf = "\n";
    
	if (this.f === WEST)
	    buf += fWest;
	else
	    buf += fEast;
    
	if (this.w === WEST)
	    buf += wWest;
	else
	    buf += wEast;
    
	if (this.g === WEST)
	    buf += gWest;
	else
	    buf += gEast;
    
	if (this.c === WEST)
	    buf += cWest;
	else
	    buf += cEast;
    
	return buf;
    };
}

function WaterJugState(x, y) {
    this.x = x;  // number of gallons in jug X
    this.y = y;  // number of gallons in jug Y

    this.equals = function (other) {     // returns true if other state
	if (other === null) return false; // has same values for x and y
	return this.x === other.x &&
	this.y === other.y;
    };

    this.width = 12;  // used to allocate space
    this.height = 8;  // for state display

    this.toString = function () {  // builds and returns a string
                                   // representing this state    
	// Start the top line
	var buf = "\n       |";

	// Complete the top line
	if (this.y === 4)
	    buf += "***|\n";
	else
	    buf += "   |\n";
    
	// Make middle three lines
	for (i = 3; i > 0; i--) {
	    buf += middleLine(i, this.x, this.y);
	}

	// Make bottom line
	buf += "+---+  +---+\n";
	buf += "  X      Y  \n";
    
	return buf;
    };
}

function middleLine(line, x, y) { // supports toString

    var buf = "|";

    if (x >= line)
	buf += "***|  |";
    else
	buf += "   |  |";

    if (y >= line)
	buf += "***|\n";
    else
	buf += "   |\n";

    return buf;
}
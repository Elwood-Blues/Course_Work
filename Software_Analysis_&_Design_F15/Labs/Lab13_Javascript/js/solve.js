var problems = [new FarmerProblem(), new WaterJugProblem(), new BridgeProblem()];
var problem = problems[0];

// Variables representing DOM elements
var selector;
var probName;
var intro;
var state;
var table;
var feedback;

function init() { // called when the page loads in the window
    table = document.getElementById("moveTable");
    selector = document.getElementById("selector");
    state = document.getElementById("state");
    probName = document.getElementById("probName");
    intro = document.getElementById("intro");
    feedback = document.getElementById("message");
    problems.forEach(function (p) { addOption(p); });
    displayProblem();
}

function addOption(prob) {
    var option = document.createElement("option");
    option.text = prob.name;
    selector.add(option, null);
}

function displayProblem() {
    problem = problems[selector.selectedIndex];
    probName.innerHTML = problem.name;
    intro.innerHTML = problem.introduction;
    state.cols = problem.currentState.width;
    state.rows = problem.currentState.height;
    updateState();
    clearTable();
    clearMessage();
    problem.moves.forEach(function (move) {
	var cell = document.createElement("td");
	cell.appendChild(createButton(move));
	var row = document.createElement("tr");
	row.appendChild(cell);
	table.appendChild(row);
    });
}

function clearTable() {
    while (table.childNodes.length !== 0) {
	table.removeChild(table.firstChild);
    }
}

var bSize = buttonSize();

function buttonSize() { // determines largest move name so move buttons
    var size = 0;       // have uniform size
    problem.moves.forEach(function (move) {
	if (move.name.length > size) {
	    size = move.name.length;
	}
    });
    return size+1;
}

function createButton(move) {
    var button = document.createElement("input");
    button.type = "button";
    button.value = move.name;
    button.style.width = bSize + "ex";
    button.onclick = function () {
	clearMessage();
	tryMove(move);
    };
    return button;
}

function tryMove(move) {
    var newState = move.doMove(problem.currentState);
    if (newState === null)
	displayMessage("That move is not possible");
    else {
	problem.currentState = newState;
	updateState();
    }
    if (problem.success()) {
	displayMessage("Congratulations. You solved the problem.");
    }
}

function displayMessage(message) {
    feedback.innerHTML = message;
}

function clearMessage() {
    displayMessage("");
}

function updateState() {
    state.value = problem.currentState;
}

function reset() {
    problem.currentState = problem.initialState;
    updateState();
}

/**
 * Created by enda1n on 12.03.2017.
 */

var inputsStrings = ['0 1', '1 2', '2 3', '2 4'];

var n = inputsStrings.length; // the number of adjacency relations

var network = new Network();

for (var i = 0; i < n; i++) {
    var inputs = inputsStrings[i].split(' ');
    var a = parseInt(inputs[0]); // the ID of a person which is adjacent to yi
    var b = parseInt(inputs[1]); // the ID of a person which is adjacent to xi

    network.add(a, b);
}
console.log(JSON.stringify(network));

// Write an action using print()
// To debug: printErr('Debug messages...');


// The minimal amount of steps required to completely propagate the advertisement


function Network() {
    this.content = [];

    this.add = function (a, b) {
        if (!this.content[a]) {
            this.content[a] = new Person(a, b);
        } else {
            this.content[a].addDirectAdjacent(b);
        }

        if (!this.content[b]) {
            this.content[b] = new Person(b);
        }
        this.content[b].addInverseAdjacent(a);
    }
}

function Person(index, b) {
    this.index = index;
    this.directAdjacent = [];
    this.inverseAdjacent = [];

    this.addDirectAdjacent = function(b) {
        this.directAdjacent.push(b);
    };

    this.addInverseAdjacent = function(a) {
        this.inverseAdjacent.push(a);
    };

    if (b !== undefined) {
        this.addDirectAdjacent(b);
    }

}
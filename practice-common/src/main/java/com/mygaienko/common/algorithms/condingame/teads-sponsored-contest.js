/**
 * Created by enda1n on 12.03.2017.
 */

//var inputsStrings = ['0 1', '1 2', '2 3', '2 4'];
//var inputsStrings = ['0 1', '1 2', '1 4', '2 3', '4 5', '4 6'];

var inputsStrings = [];
inputsStrings.push();

var n = inputsStrings.length; // the number of adjacency relations

var network = new Network();

for (var i = 0; i < n; i++) {
    var inputs = inputsStrings[i].split(' ');
    var a = parseInt(inputs[0]); // the ID of a person which is adjacent to yi
    var b = parseInt(inputs[1]); // the ID of a person which is adjacent to xi

    network.add(a, b);
}
console.log(JSON.stringify(network));

network.countBroadcast();

console.log(network.findMinBroadcast());

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
    };

    this.countBroadcast = function() {
        this.countAllDirectBroadcast();
        this.countAllInverseBroadcast();
    };

    this.countAllDirectBroadcast = function() {
        this.countDirectBroadcast(0);
    };

    this.countDirectBroadcast = function(index) {

        var currentPerson = this.content[index];
        var directAdjacent = currentPerson.directAdjacent;

        var directBroadcast;

        if (currentPerson.directBroadcast !== undefined) {
            directBroadcast = currentPerson.directBroadcast;
        } else {

            directBroadcast = 0;
            for (var i = 0; i < directAdjacent.length; i++) {
                var currentBroadcast = this.countDirectBroadcast(directAdjacent[i]);
                if (directBroadcast <= currentBroadcast) {
                    directBroadcast = currentBroadcast;
                }
            }
            currentPerson.directBroadcast = directBroadcast;
        }
        return directBroadcast + 1;
    };

    this.countAllInverseBroadcast = function() {

        for (var i = 0; i < this.content.length; i++) {
            var person = this.content[i];
            if (person !== undefined) {
                this.countInverseBroadcast(person.index);
            }
        }

    };

    this.countInverseBroadcast = function(index) {
        var currentPerson = this.content[index];
        var inverseAdjacent = currentPerson.inverseAdjacent;

        var inverseBroadcast;
        if (currentPerson.inverseBroadcast !== undefined) {
            inverseBroadcast = currentPerson.inverseBroadcast;
        } else {
            inverseBroadcast = 0;
            //max beetween
            //inverse broadcast of parent node
            //vs direct broadcast of parent node but another connection
            //+1
            for (var i = 0; i < inverseAdjacent.length; i++) {
                var parent = this.content[inverseAdjacent[i]];
                inverseBroadcast = this.countInverseBroadcast(parent.index);

                var children = parent.directAdjacent.filter(function(id){
                    return id != index;
                });

                for (var j = 0; j < children.length; j++) {
                    var child = this.content[children[i]];
                    var toChildrenDirectBroadcast = child.directBroadcast + 1;

                    if (inverseBroadcast <= toChildrenDirectBroadcast) {
                        inverseBroadcast = toChildrenDirectBroadcast;
                    }
                }
            }

            if (inverseAdjacent.length > 0) {
                inverseBroadcast++;
            }
            currentPerson.inverseBroadcast = inverseBroadcast;

        }
        return inverseBroadcast;

    };

    this.findMinBroadcast = function() {
        var minBroadcast = Number.MAX_VALUE;
        for (var i = 0; i < this.content.length; i++) {
            var person = this.content[i];
            if (person !== undefined) {
                var broadcastCompletedTime = Math.max(person.directBroadcast, person.inverseBroadcast);

                if (broadcastCompletedTime <= minBroadcast) {
                    minBroadcast = broadcastCompletedTime;
                }
            }
        }

        return minBroadcast;
    }
}

function Person(index, b) {
    this.index = index;
    this.directAdjacent = [];
    this.inverseAdjacent = [];

    this.directBroadcast = undefined;
    this.inverseBroadcast = undefined;

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
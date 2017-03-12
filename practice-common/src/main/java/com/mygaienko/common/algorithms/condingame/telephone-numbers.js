/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var N = 2;
var storage = new Storage;
var telephones = ['0123456789', '0123'];

for (var i = 0; i < N; i++) {
    var telephone = telephones[i];
    storage.addContact(telephone);
}

console.log(storage.size);

function Storage() {
    this.size = 0;
    this.bases = [];

    this.addContact = function(number) {
        var numbers = number.split("");

        var numberNode;
        for (var i = 0; i < numbers.length; i++) {
            var numberToAdd = numbers[i];
            var currentNumbers;
            if (i == 0) {
                currentNumbers = this.bases;
            } else {
                currentNumbers = numberNode.nextNodes;
            }

            numberNode = this.getOrCreateNumberNode(currentNumbers, numberToAdd);
        }
    }

    this.getOrCreateNumberNode = function(currentNumbers, number) {
        var baseNode;
        for (var i = 0; i < currentNumbers.length; i++) {
            if (currentNumbers[i].number == number) {
                baseNode = currentNumbers[i];
            }
        }

        if (!baseNode) {
            baseNode = new Node(number);
            currentNumbers.push(baseNode);
            this.size++;
        }

        return baseNode;
    }

}


function Node(number) {
    this.number = number;
    this.nextNodes = [];

    this.addNextNode = function(node) {
        this.nextNodes.push(node);
    }

}



// Write an action using print()
// To debug: printErr('Debug messages...');
//CHOOSED

// The number of elements (referencing a number) stored in the structure.

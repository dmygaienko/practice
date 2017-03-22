/**
 * Created by enda1n on 12.03.2017.
 */
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var R = 1;
var L = 11;

// Write an action using print()
// To debug: printErr('Debug messages...');
var allSeries = [];
allSeries[0] = [R];
//printErr(R);
//printErr(allSeries[0]);

function notLastIndex(index, array) {
    return index < array.length - 1;
}

function lastIndex(index, array) {
    return index == array.length - 1;
}

for (var i = 1; i < L; i++) {
    var prevSeries = allSeries[i-1];
    var nextSeries = [];


    var value = undefined;
    var occurrence = 0;
    for (var j = 0; j < prevSeries.length; j++) {
        var nextValue = prevSeries[j];

        if ((!value || value == nextValue) && notLastIndex(j, prevSeries)) {
            occurrence++;

        } else if ((value == nextValue || !value) && lastIndex(j, prevSeries)) {
            occurrence++;
            nextSeries.push(occurrence, nextValue);

        } else if (value != nextValue && notLastIndex(j, prevSeries)) {
            nextSeries.push(occurrence, value);
            occurrence = 1;

        } else if (value != nextValue && lastIndex(j, prevSeries)) {
            nextSeries.push(occurrence, value);
            occurrence = 1;
            nextSeries.push(occurrence, nextValue);
        }

        value = nextValue;
    }

    allSeries[i] = nextSeries;
}


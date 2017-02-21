/**
 * https://www.youtube.com/watch?v=PMze6o7ME4Y dijkstra visualization
 *
 * http://codereview.stackexchange.com/questions/122115/implementation-of-dijkstras-algorithm-in-javascript-that-returns-both-shortestd
 *
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var inputs = readline().split(' ');
var N = parseInt(inputs[0]); // the total number of nodes in the level, including the gateways
var L = parseInt(inputs[1]); // the number of links
var E = parseInt(inputs[2]); // the number of exit gateways


var links = [];
var nodes = [];
for (var i = 0; i < L; i++) {
    var inputs = readline().split(' ');
    links.push(inputs);
    var N1 = parseInt(inputs[0]);
    nodes[N1] = 1;// N1 and N2 defines a link between these nodes
    var N2 = parseInt(inputs[1]);
    nodes[N2] = 1;
}
printErr(JSON.stringify(links));
printErr(JSON.stringify(nodes));

var gateways = [];
for (var i = 0; i < E; i++) {
    var EI = parseInt(readline()); // the index of a gateway node
    gateways.push(EI.toString());
}
//print(gateways);

var linksToGateways = [];
for (var li = 0; li < links.length; li++) {
    var link = links[li];

    var zeroNodeAsGtwIndex = gateways.indexOf(link[0].toString());
    var firstNodeAsGtwIndex = gateways.indexOf(link[1].toString());

    if (zeroNodeAsGtwIndex > -1) {
        var temp = link[0];
        link[0] = link[1];
        link[1] = temp;
        linksToGateways.push(link);
    } else if (firstNodeAsGtwIndex > -1) {
        linksToGateways.push(link);
    }
}
printErr(JSON.stringify(linksToGateways));
linksToGateways.sort();
printErr(JSON.stringify(linksToGateways));

// game loop
while (true) {
    var SI = parseInt(readline());

    var severedLink = undefined;
    for (var ltgi = 0; ltgi < linksToGateways.length; ltgi++) {
        if (linksToGateways[ltgi][0] == SI){
            severedLink = linksToGateways[ltgi];
            linksToGateways.splice(ltgi, 1);
            break;
        }
    }

    if (severedLink == undefined) {
        severedLink = linksToGateways.shift();
    }
    print(severedLink[0] + ' ' + severedLink[1]);
}/**
 * Created by enda1n on 21.02.2017.
 */

var testUtils = require("./test-utils.js");

function readline() { return testUtils.readline(); }
function print(msg) { testUtils.print(msg); }
function printErr(msg) { testUtils.printErr(msg); }
// ////////////////////////////////////////


var inputs = readline().split(' ');
//printErr('input ' + inputs);
var N = parseInt(inputs[0]); // the total number of nodes in the level, including the gateways
var L = parseInt(inputs[1]); // the number of links
var E = parseInt(inputs[2]); // the number of exit gateways

var INFINITY = 1/0;
var graph = new DirectedGraph(N);

for (var i = 0; i < L; i++) {
    var inputs = readline();
    //printErr('input ' + inputs);
    inputs = inputs.split(' ');
    //printErr(JSON.stringify(inputs));
    var N1 = parseInt(inputs[0]);
    var N2 = parseInt(inputs[1]);
    graph.addVertex(N1, N2);
}
//printErr(JSON.stringify(graph));
//printErr(JSON.stringify(djikstra(graph, '4').shortestPaths));
//printErr(JSON.stringify(djikstra(graph, '4').shortestDistances));
//printErr(JSON.stringify(graph));

var gateways = initGateways();
graph.addGateways(gateways);
//printErr('graph: ' + JSON.stringify(graph));

//printErr(JSON.stringify(graph));


// game loop
while (true) {
    //printErr(JSON.stringify(graph));
    //printErr('graph.dangers ' + JSON.stringify(graph.dangers));
    //initGateways();
    
    var step = readline();
   // printErr('input ' + step);
    var SI = parseInt(step);
    //printErr('SI: ' + SI);

    var start = undefined;
    var end = undefined;

    var adjGateway = graph.getAdjacentGateway(SI);
    if (adjGateway !== undefined) {
        //printErr('adjGateway !== undefined');
        start = SI;
        end = adjGateway;
    } else {
        var result = dijkstra(graph, SI);
        //printErr('result: ' + JSON.stringify(result));
        var linkToCut = getMostDangerousLink(result);
        start = linkToCut.start;
        end = linkToCut.end;
    }

    graph.deleteVertex(start, end);
    print(start + ' ' + end);
}

function getMostDangerousLink(result) {
    var mostDanger = 0;
    var mostDangerPathIndex = 0;

    result.shortestDistances.forEach(function (distance, index) {
        if ((mostDanger == 0 && distance.danger > mostDanger) ||
            (distance.value < distance.danger)) {
            mostDanger = distance.danger;
            mostDangerPathIndex = index;
        }
    });

    var dangerousPath = result.shortestPaths[mostDangerPathIndex];
    var dangers = dangerousPath.danger;
    var start = undefined;
    var gateway = undefined;

    //get nearest danger
    for (var i = 1; i < dangerousPath.length; i++) {
        var nextStep = dangerousPath[i];

        var adjGw = dangers[nextStep];
        if (adjGw === undefined) {

        } else if (adjGw.length == 1 && start === undefined) {
            start = nextStep;
        } else if (adjGw.length > 1) {
            start = nextStep;
            break;
        }
    }

    if (start !== undefined) {
        gateway = dangers[start].shift();
    }

    return {start: start, end: gateway};
}

function initGateways(){
    var result = [];
    for (var i = 0; i < E; i++) {
        var EI = parseInt(readline()); // the index of a gateway node
        result.push(EI);
        //printErr("gateway: " + EI);
    }
    return result;
}

function DirectedGraph(N){
    var self = this;

    this.totalNodes = N;
    this.adjacency = [];
    this.gateways = {};
    this.dangers = {};
    
    this.addVertex = function(firstNode, secondNode){
        addAdjacency(this.adjacency, firstNode, secondNode);
        addAdjacency(this.adjacency, secondNode, firstNode);
    };

    this.deleteVertex = function(firstNode, secondNode){
        this.removeAdjacency(firstNode, secondNode);
        this.removeAdjacency(secondNode, firstNode);
    };

    this.deleteDanger = function(firstNode, secondNode){
        var allDangers = this.dangers[firstNode];
        var dangerIndex = allDangers.indexOf(secondNode);
        if (dangerIndex > -1) {
            allDangers.splice(index, 1);
        }
    };

    this.getDanger = function(node){
        var adjGateways = self.dangers[node];
        return  adjGateways === undefined ? 0 : adjGateways.length;
    };

    this.getAdjacentGateway = function(node){
        var adjGateways = self.dangers[node];
        return  adjGateways === undefined ? undefined : adjGateways[0];
    };

    this.addGateways = function(gateways){
        gateways.forEach(function(gw) {
            self.gateways[parseInt(gw)] = gw;
        });
        this.initDangers();
    };

    this.isGateway = function(node){
        return self.gateways[node] !== undefined;
    };

    this.initDangers = function(){
        Object.keys(self.gateways).forEach(function(gw){
            var dangerousNodes = self.adjacency[gw];
            if (dangerousNodes !== undefined && dangerousNodes.length > 0) {
                
                dangerousNodes.forEach(function(dangerousNode){
                    if (self.dangers[dangerousNode] === undefined) {
                        self.dangers[dangerousNode] = [];
                    }
                    self.dangers[dangerousNode].push(gw);
                })
            }
        });
    };

    this.removeAdjacency = function(firstNode, secondNode){
        var neighbours = this.adjacency[firstNode];

        if (neighbours !== undefined) {
            var index = neighbours.indexOf(parseInt(secondNode));
            if (index > -1) {
                neighbours.splice(index, 1);
            }
        }
    }
}

function addAdjacency(array, firstNode, secondNode){
    if (array[firstNode] === undefined) {
        array[firstNode] = [];
    }
    array[firstNode].push(secondNode);
}

function dijkstra(graph, startNode){

    var distance = [],       // shortest known distance from "s"
        previous = [],       // preceeding node in path
        queue  = {},       // all false initially
        shortestPaths = [];

    for (var vertex in graph.adjacency) {
        if (graph.isGateway(vertex)){
            continue;
        }
        distance[vertex] = {value: Infinity};
        previous[vertex] = null;
        queue[vertex] = graph.adjacency[vertex].slice()
            .filter(function (node) {
                return !graph.isGateway(node);
            });
        shortestPaths[vertex] = [];
    }

    distance[startNode] = {value: 0, danger: 0};
    previous[startNode] = 0;

    while (Object.keys(queue).length !== 0) {
        var next = minDistanceVertex(distance, queue);

        var neighbors = graph.adjacency[next];
        for (var n = 0; neighbors !== undefined && n < neighbors.length; n++) {
            var neighbor = neighbors[n];
            var neighborDistance = distance[next].value + 1; //1 is hardcode
            var neighborDanger = distance[next].danger + graph.getDanger(neighbor);

            var distanceN = distance[neighbor];
            if (( distanceN === undefined
                    || distanceN.value > neighborDistance
                    || (distanceN.value === neighborDistance && distanceN.danger < neighborDanger ))
                    && !graph.isGateway(neighbor)) {

                if (distance[neighbor] === undefined) {
                    distance[neighbor] = {value: 0, danger: 0}
                }

                distance[neighbor].value = neighborDistance;
                distance[neighbor].danger = neighborDanger;
                previous[neighbor] = next;
            }
        }
    }

    fillShortestPaths(previous, shortestPaths, startNode, distance, graph.dangers);
    
   // distance[startNode] = Number.MAX_VALUE;
    distance[startNode] = Infinity;
    return {
        shortestPaths: shortestPaths,
        shortestDistances: distance
    }
}

function fillShortestPaths(previous, shortestPaths, startVertex, distance, dangers) {
    for (var i = 0; i < previous.length; i++) {
        var node = i;
        var path = [];
        path.danger = {};
        path.danger.size = 0;
        shortestPaths[node] = path;

        while(previous[node] !== undefined) {
            path.push(node);
            if (dangers[node] !== undefined) {
                path.danger[node] = dangers[node];
                path.danger.size += dangers[node].length;
            }
            node = previous[node];
            if (node == startVertex) break;
        }

        //gets the starting node in there as well if there was a path from it
        if (!!distance[node] && distance[node].value === 0) {
            path.push(node);
        }
        path.reverse();
    }
}

//find the minimal distance vertex to prevent from hooks
function minDistanceVertex(distance, queue){
    var minVertexDistance = Infinity;
    var minVertex = -1; // graph not connected, or no unvisited vertices

    //for (var i = 0; i < query.length; i++) {
    for (var i in queue) {
        var nodeDistance = distance[i];

        if (nodeDistance.value < minVertexDistance) {
            minVertex = i;
            minVertexDistance = distance[i].value;
        }
    }

    delete queue[minVertex];
    return minVertex;
}
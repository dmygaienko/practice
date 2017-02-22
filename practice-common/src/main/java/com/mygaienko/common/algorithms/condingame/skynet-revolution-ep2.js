var vertices = [[6,2],[7,3],[6,3],[5,3],[3,4],[7,1],[2,0],[0,1],[0,3],[1,3],[2,3],[7,4],[6,5]];

var graph = new DirectedGraph();
vertices.forEach(function (value) {
    graph.addVertex(value[0], value[1]);
});

console.log(JSON.stringify(dijkstra(graph, 0)));

function DirectedGraph(){
    this.adjacency = [];
    this.addVertex = function(firstNode, secondNode){
        addAdjacency(this.adjacency, firstNode, secondNode);
        addAdjacency(this.adjacency, secondNode, firstNode);
    }
}

function addAdjacency(array, firstNode, secondNode){
    if (array[firstNode] === undefined) {
        array[firstNode] = [];
    }
    array[firstNode].push(secondNode);
}

function dijkstra(graph, startNode){

    var dist = [],       // shortest known distance from "s"
        prev = [],       // preceeding node in path
        visited  = [],   // all false initially
        shortestPaths = [];

    initVisited(visited);
    initDistances(dist);

    dist[startNode] = 0;
    prev[startNode] = 0;

    for (var i = 0; i < dist.length; i++) {
        var next = minVertex(dist, visited);
        visited[next] = true;

        var neighbors = graph.adjacency[i];
        for (var n = 0; n < neighbors.length; n ++) {
            var neighbor = neighbors[n];
            var neighborDistance = dist[next] + 1; //1 is hardcode

            if (dist[neighbor] > neighborDistance || dist[neighbor] === undefined) {
                dist[neighbor] = neighborDistance;
                prev[neighbor] = next;
            }
        }
    }

    fillShortestPaths(prev, shortestPaths, startNode, dist);

    return {
        shortestPaths: shortestPaths,
        shortestDistances: dist
    }
}
//get startNode with distance of 0 at first call
function minVertex(dist, visited){
    var minVertexDistance = Number.MAX_VALUE;
    var minVertex = -1; // graph not connected, or no unvisited vertices

    for (var i = 0; i < dist.length; i++) {
        if (notVisited(visited, i) && dist[i] < minVertexDistance) {
            minVertex = i;
            minVertexDistance = dist[i];
        }
    }

    return minVertex;
}

function notVisited(visited, i) {
    return !visited[i];
}

function fillShortestPaths(previous, shortestPaths, startVertex, dist) {
    for (var i = 0; i < previous.length; i++) {
        var node = i;
        var path = [];
        shortestPaths[node] = path;

        while(previous[node] !== undefined) {
            path.push(node);
            node = previous[node];
            if (node == startVertex) break;
        }

        //gets the starting node in there as well if there was a path from it
        if (dist[node] === 0) {
            path.push(node);
        }
        path.reverse();
    }
}

function initVisited(visited) {
    for (var i = 0; i < visited.length; i++) {
        visited[i] = false;
    }
}

function initDistances(dist) {
    for (var i = 0; i < dist.length; i++) {
        dist[i] = Number.MAX_VALUE;
    }
}

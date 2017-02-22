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

    var dist = [],
        prev = [],
        queue = [],
        shortestPaths = [];



    for (var node = 0; node < graph.adjacency.length; node++){
        dist[node] = Infinity;
        prev[node] = null;
        queue[node] = clone(graph.adjacency[node]);
        shortestPaths[node] = [];
    }

    //should use startNode as first
    dist[startNode] = 0;

    var nextQueue;
    while (!!(nextQueue = queue.pop())) {

        var leastNode;
        while (!!(leastNode = nextQueue.pop())) {
            if (!dist[leastNode]) {
                dist[leastNode] = 1;
            }

            var neighbor;
            while (graph.adjacency[leastNode] && (neighbor = graph.adjacency[leastNode].pop())) {
                var alt = dist[leastNode] + 1;
                if (alt <= dist[neighbor]) {
                    dist[neighbor] = alt;
                    prev[neighbor] = neighbor;

                }
            }
        }
    }

    fillShortestPaths(prev, shortestPaths, startNode, dist);

    return {
        shortestPaths: shortestPaths,
        shortestDistances: dist
    }
}

function fillShortestPaths(previous, shortestPaths, startVertex, dist) {
    for (var node in shortestPaths) {
        var path = shortestPaths[node];

        while(previous[node]) {
            path.push(node);
            node = previous[node];
        }

        //gets the starting node in there as well if there was a path from it
        if (dist[node] === 0) {
            path.push(node);
        }
        path.reverse();
    }
}

function clone(array){
    var result = []
    array.forEach(function(value) {result.push(value)});
    return result;
}

//my attempt at coding the wiki pseudocode version of Dijkstra in Javascript, that returns
//both the shortest paths AND shortest distances to each vertex from the source vertex input

var INFINITY = 1/0;

function DirectedGraph(){
    this.vertices = {};
    this.addVertex = function(name, edges){
        edges = edges || null;
        this.vertices[name] = edges;
    }
}

function findSmallest(dist, q) {
    var min = Infinity;
    var minNode;

    for (var node in q) {
        if (dist[node] <= min) {
            min = dist[node]
            minNode = node;
        }
    }

    delete q[minNode]
    return minNode;
}

function djikstra(graph, startVertex) {
    var dist = {};
    var prev = {};
    var q = {};
    var shortestPaths = {};

    for (var vertex in graph.vertices) {
        dist[vertex] = INFINITY;
        prev[vertex] = null;
        q[vertex] = graph.vertices[vertex];
        shortestPaths[vertex] = [];
    }

    dist[startVertex] = 0;

    while (Object.keys(q).length !== 0) {
        var smallest = findSmallest(dist, q);
        var smallestNode = graph.vertices[smallest]
        //searches for the vertex u in the vertex set Q that has the least dist[smallest] value.

        for (var neighbor in smallestNode) {
            var alt = dist[smallest] + smallestNode[neighbor];
            //smallestNode[neighbor] is the distance between smallest and neighbor
            if (alt < dist[neighbor]) {
                dist[neighbor] = alt
                prev[neighbor] = smallest
            }
        }
    }

    getShortestPaths(prev, shortestPaths, startVertex, dist)

    return {
        shortestPaths: shortestPaths,
        shortestDistances: dist
    }
}

function getShortestPaths(previous, shortestPaths, startVertex, dist) { debugger
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

var graph = new DirectedGraph();

graph.addVertex('S', {V: 1, W: 4});
graph.addVertex('W', {T: 3});
graph.addVertex('V', {W: 2, T: 6});
graph.addVertex('T');


console.log(djikstra(graph, 'S'));

/*

----------------->

{ shortestPaths:
{ S: [ 'S' ],
    W: [ 'S', 'V', 'W' ],
    V: [ 'S', 'V' ],
    T: [ 'S', 'V', 'W', 'T' ] },
    shortestDistances: { S: 0, W: 3, V: 1, T: 6 } }*/

package com.mygaienko.common.graph;

import java.util.*;

/**
 * Created by enda1n on 13.12.2016.
 */
public class UndirectedGraph {

    private HashMap<String, List<String>> vertexMap = new HashMap<>();

    public void addVertex(String vertexName) {
        if (!hasVertex(vertexName)) {
            vertexMap.put(vertexName, new ArrayList<String>());
        }
    }

    public boolean hasVertex(String vertexName) {
        return vertexMap.containsKey(vertexName);
    }

    public boolean hasEdge(String vertexName1, String vertexName2) {
        if (!hasVertex(vertexName1)) return false;

        List<String> edges = vertexMap.get(vertexName1);
        return Collections.binarySearch(edges, vertexName2) != -1;
    }

    public void addEdge(String vertexName1, String vertexName2) {
        if (!hasVertex(vertexName1)) addVertex(vertexName1);
        if (!hasVertex(vertexName2)) addVertex(vertexName2);

        List<String> edges1 = vertexMap.get(vertexName1);
        edges1.add(vertexName2);
        Collections.sort(edges1);

        List<String> edges2 = vertexMap.get(vertexName2);
        edges2.add(vertexName1);
        Collections.sort(edges2);
    }

    public Map<String, List<String>> getVertexMap() {
        return vertexMap;
    }

}

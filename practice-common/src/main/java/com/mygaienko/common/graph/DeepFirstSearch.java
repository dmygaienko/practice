package com.mygaienko.common.graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by enda1n on 13.12.2016.
 */
public class DeepFirstSearch {

    public static Map<String, Mark> execute(UndirectedGraph graph) {
        Map<String, Mark> visitedMap = new LinkedHashMap<>();
        // time counter

        AtomicLong counter = new AtomicLong(1);

        Map<String, List<String>> vm = graph.getVertexMap();

        List<String> vertexList = new ArrayList<>(vm.size());
        vertexList.addAll(vm.keySet());
        Collections.sort(vertexList);

        for (String vertexName : vertexList) {
            dfs(graph, visitedMap, counter, vertexName);
        }
        return visitedMap;
    }

    private static void dfs(UndirectedGraph graph, Map<String, Mark> visitedMap, AtomicLong counter, String vertexName) {
        if (visitedMap.containsKey(vertexName)) return;

        // set pre (time of enter)
        visitedMap.put(vertexName, new Mark(counter.get(), -1));
        counter.incrementAndGet();

        // retrieve adjacent vertices
        Map<String, List<String>> vm = graph.getVertexMap();
        List<String> adjacentVertices = vm.get(vertexName);

        for (String adjacentVertex : adjacentVertices) {
            if (visitedMap.containsKey(adjacentVertex)) continue;

            dfs(graph, visitedMap, counter, adjacentVertex);
        }

        // set post (time of exit)
        Mark m = visitedMap.get(vertexName);
        m.post =  counter.getAndIncrement();
    }

    static class Mark {
        public Mark(long pre, long post) {
            this.pre  = pre;
            this.post = post;
        }
        public long pre;
        public long post;
    }
}

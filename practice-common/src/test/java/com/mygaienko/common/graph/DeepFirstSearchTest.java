package com.mygaienko.common.graph;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 13.12.2016.
 */
public class DeepFirstSearchTest {

    @Test
    public void testDfs() throws Exception {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "E");
        graph.addEdge("B", "C");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("E", "F");
        graph.addEdge("F", "I");

        graph.addEdge("D", "G");
        graph.addEdge("D", "H");
        graph.addEdge("G", "H");

        Map<String, DeepFirstSearch.Mark> visited = DeepFirstSearch.execute(graph);

        for (Map.Entry<String, DeepFirstSearch.Mark> entry : visited.entrySet()) {
            DeepFirstSearch.Mark m = entry.getValue();
            System.out.format("%1$s : (%2$d, %3$d)\n", entry.getKey(), m.pre, m.post);
        }
    }
}
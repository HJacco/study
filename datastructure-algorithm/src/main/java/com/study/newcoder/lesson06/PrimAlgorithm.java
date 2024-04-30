package com.study.newcoder.lesson06;

import java.util.List;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimAlgorithm {

    public static Set<Edge> prim(Graph g) {
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>();
        HashSet<Node> accessedNodes = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        // for循环主要是为了兼容不连通的情况
        for (Node node : g.nodes.values()) {
            if (!accessedNodes.contains(node)) {
                accessedNodes.add(node);
                List<Edge> edgesOfStartNode = node.edges;
                edgePriorityQueue.addAll(edgesOfStartNode);
                while (!edgePriorityQueue.isEmpty()) {
                    Edge e = edgePriorityQueue.poll();
                    if (!accessedNodes.contains(e.to)) {
                        result.add(e);
                        accessedNodes.add(e.to);
                        edgePriorityQueue.addAll(e.to.edges);
                    }
                }
            }
        }
        return result;
    }
}

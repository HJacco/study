package com.study.newcoder.lesson06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {
    public static Map<Node, Integer> dijkstra(Node start) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);

        Set<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinNodeAndNotSelected(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge e : minNode.edges) {
                Node nextNode = e.to;
                if (!distanceMap.containsKey(nextNode)) {
                    distanceMap.put(nextNode, distance + e.weight);
                } else {
                    distanceMap.put(nextNode, Math.min(distanceMap.get(nextNode), distance + e.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinNodeAndNotSelected(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinNodeAndNotSelected(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        Node result = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> e : distanceMap.entrySet()) {
            Integer distance = e.getValue();
            Node node = e.getKey();
            if (distance < minDistance && !selectedNodes.contains(node)) {
                result = node;
                minDistance = distance;
            }
        }
        return result;
    }
}

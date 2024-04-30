package com.study.newcoder.review.lesson06;

import com.study.newcoder.lesson06.Graph;
import com.study.newcoder.lesson06.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.List;

public class TopologySort {

    public static List<Node> sort(Graph g) {
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();

        for (Node node : g.nodes.values()) {
            if (node.in == 0) {
                zeroInQueue.offer(node);
            }
            inMap.put(node, node.in);
        }

        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node n = zeroInQueue.poll();
            result.add(n);
            for (Node nn : n.nexts) {
                inMap.put(nn, inMap.get(nn) - 1);
            }
        }
        return result;
    }
}

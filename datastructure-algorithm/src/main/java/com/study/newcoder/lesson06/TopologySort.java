package com.study.newcoder.lesson06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 图拓扑排序
 */
public class TopologySort {

    /**
     * 1、首先将图的所有节点和入度记录成map
     * 2、依次找出入度为0的节点，并将节点移除，更新其他节点入度
     */
    public static List<Node> sort(Graph graph) {
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zero = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zero.offer(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (!zero.isEmpty()) {
            Node current = zero.poll();
            result.add(current);
            // 更新相邻节点的入度
            for (Node next : current.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zero.offer(next);
                }
            }
        }

        return result;
    }
}

package com.study.newcoder.review.lesson06;

import com.study.newcoder.lesson06.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphTravel {
    public static void dfs(Node start) {
        Stack<Node> stack = new Stack<>();
        Set<Node> accessedNodes = new HashSet<>();

        stack.push(start);
        accessedNodes.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            List<Node> neighbours = current.nexts;
            for (Node n : neighbours) {
                if (!accessedNodes.contains(n)) {
                    stack.push(current);
                    stack.push(n);
                    accessedNodes.add(n);
                    System.out.println(n.value);
                    break;
                }
            }
        }
    }

    public static void bfs(Node start) {
        if (null == start) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> accessed = new HashSet<>();
        accessed.add(start);
        queue.offer(start);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println(current.value);
            List<Node> neighbours = current.nexts;
            for (Node n : neighbours) {
                if (!accessed.contains(n)) {
                    queue.offer(n);
                    accessed.add(n);
                }
            }
        }
    }
}

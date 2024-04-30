package com.study.newcoder.lesson06;

import java.util.List;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图搜索
 * 1、广度优先
 * 2、深度优先
 */
public class GraphSearch {

    /**
     * 广度优先遍历
     */
    public static void BFS(Node start) {
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

    /**
     * 深度优先遍历
     */
    public static void dfs(Node start) {
        if (null == start) {
            return;
        }
        Stack<Node> helpStack = new Stack<>();
        HashSet<Node> accessedNode = new HashSet<>();
        helpStack.push(start);
        accessedNode.add(start);
        System.out.println(start.value);
        while (!helpStack.isEmpty()) {
            Node current = helpStack.pop();
            List<Node> neighbours = current.nexts;
            for (Node n : neighbours) {
                if (!accessedNode.contains(n)) {
                    helpStack.push(current);
                    helpStack.push(n);
                    accessedNode.add(n);
                    System.out.println(n.value);
                    break;
                }
            }
        }
    }
}

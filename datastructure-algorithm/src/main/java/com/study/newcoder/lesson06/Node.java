package com.study.newcoder.lesson06;

import java.util.ArrayList;
import java.util.List;

public class Node {
    /**
     * 值
     */
    public int value;

    /**
     * 出度
     */
    public int out;

    /**
     * 入度
     */
    public int in;

    /**
     * 从当前节点发散出去的边的点
     */
    public List<Node> nexts;

    /**
     * 边
     */
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}

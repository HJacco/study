package com.study.newcoder.lesson06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 无向图最小生成树
 * 每次拿权重最小的边，同时通过并查集检查是否有环
 */
public class KruskalAlgorithm {

    static class UnionFind {
        public Map<Node, List<Node>> sets = new HashMap<>();

        public UnionFind(Collection<Node> nodes) {
            for (Node n : nodes) {
                sets.put(n, new ArrayList<>());
            }
        }

        public boolean isSameSet(Node n1, Node n2) {
            return sets.get(n1) == sets.get(n2);
        }

        public void union(Node n1, Node n2) {
            List<Node> set1 = sets.get(n1);
            List<Node> set2 = sets.get(n2);

            set1.addAll(set2);
            sets.put(n2, set1);
        }

    }

    public static Set<Edge> kruskal(Graph g) {
        UnionFind unionFind = new UnionFind(g.nodes.values());
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (Edge e : g.edges) {
            edges.add(e);
        }
        Set<Edge> result = new HashSet<>();
        while (!edges.isEmpty()) {
            Edge e = edges.poll();
            if (!unionFind.isSameSet(e.from, e.to)) {
                result.add(e);
                unionFind.union(e.from, e.to);
            }
        }
        return result;
    }

}

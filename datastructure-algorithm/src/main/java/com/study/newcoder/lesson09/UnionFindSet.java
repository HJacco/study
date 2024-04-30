package com.study.newcoder.lesson09;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnionFindSet<V> {

    /**值和包装节点的映射*/
    private Map<V, Element<V>> valueMap;

    /**代表节点*/
    private Map<Element<V>, Element<V>> fatherMap;

    /**代表节点集合大小*/
    private Map<Element<V>, Integer> sizeMap;

    public UnionFindSet(List<V> values) {
        this.valueMap = new HashMap<>();
        this.fatherMap = new HashMap<>();
        this.sizeMap = new HashMap<>();
        for (V v : values) {
            Element<V> element = new Element<>(v);
            valueMap.put(v, element);
            fatherMap.put(element, element);
            sizeMap.put(element, 1);
        }
    }

    public Element<V> findHead(Element<V> element) {
        Stack<Element<V>> stack = new Stack<>();
        while (element != fatherMap.get(element)) {
            stack.push(element);
            element = fatherMap.get(element);
        }
        while (!stack.isEmpty()) {
            fatherMap.put(stack.pop(), element);
        }
        return element;
    }

    /**
     * 判断两个点是不是相同集合，只要找他们所在集合的代表节点
     */
    public boolean isSameSet(V v1, V v2) {
        if (valueMap.containsKey(v1) && valueMap.containsKey(v2)) {
            return findHead(valueMap.get(v1)) == findHead(valueMap.get(v2));
        }
        return false;
    }

    /**
     * 两个点所在集合合并
     */
    public void union(V v1, V v2) {
        if (valueMap.containsKey(v1) && valueMap.containsKey(v2)) {
            Element<V> head1 = findHead(valueMap.get(v1));
            Element<V> head2 = findHead(valueMap.get(v2));
            if (head1 != head2) {
                Integer size1 = sizeMap.get(head1);
                Integer size2 = sizeMap.get(head2);
                Element<V> bigger = size1 >= size2 ? head1 : head2;
                Element<V> small = size1 >= size2 ? head2 : head1;
                fatherMap.put(small, bigger);
                sizeMap.put(bigger, size1 + size2);
                sizeMap.remove(small);
            }
        }
    }

    static class Element<V> {
        public V v;

        public Element(V v) {
            this.v = v;
        }
    }
}

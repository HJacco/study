package com.study.newcoder.review.lesson09;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFindSet<V> {

    public HashMap<V, Element<V>> valueMap;

    public HashMap<Element<V>, Element<V>> fatherMap;

    public HashMap<Element<V>, Integer> sizeMap;

    public UnionFindSet(List<V> values) {
        for (int i = 0; i < values.size(); i ++) {
            Element<V> element = new Element<>(values.get(i));
            valueMap.put(values.get(i), element);
            fatherMap.put(element, element);
            sizeMap.put(element, 1);
        }
    }

    static class Element<V> {
        public V v;

        public Element(V v) {
            this.v = v;
        }
    }

    public Element<V> findHead(Element<V> element) {
        Stack<Element<V>> stack = new Stack<>();
        Element<V> current = fatherMap.get(element);
        while (current != element) {
            stack.push(current);
            current = fatherMap.get(current);
        }

        // 路径压缩
        while (!stack.isEmpty()) {
            fatherMap.put(stack.pop(), current);
        }
        return current;
    }

    public void union(V v1, V v2) {
        if (valueMap.containsKey(v1) && valueMap.containsKey(v2)) {
            Element<V> v1Ele = valueMap.get(v1);
            Element<V> v2Ele = valueMap.get(v2);
            Element<V> head1 = findHead(v1Ele);
            Element<V> head2 = findHead(v2Ele);

            if (head1 != head2) {
                Integer size1 = sizeMap.get(head1);
                Integer size2 = sizeMap.get(head2);
                Element<V> bigger = size1 > size2 ? head1 : head2;
                Element<V> smaller = bigger == head1 ? head2 : head1;
                fatherMap.put(smaller, bigger);
                sizeMap.put(bigger, size1 + size2);
                sizeMap.remove(smaller);
            }
        }
    }


    public boolean isSameSet(V v1, V v2) {
        if (valueMap.containsKey(v1) && valueMap.containsKey(v2)) {
            Element<V> v1Ele = valueMap.get(v1);
            Element<V> v2Ele = valueMap.get(v2);
            return findHead(v1Ele) == findHead(v2Ele);
        }
        return false;
    }
}

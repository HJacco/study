package com.study.newcoder.review.lesson07;

public class PrefixTree {

    static class Node {
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            nexts = new Node[26];
        }
    }

    public Node root;

    public PrefixTree() {
        this.root = new Node();
    }

    public void insert(String str) {
        if (null == str) {
            return;
        }
        char[] chs = str.toCharArray();
        Node node = this.root;
        node.pass ++;
        for (int i = 0; i < chs.length; i ++) {
            int index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new Node();
            }
            node = node.nexts[index];
            node.pass ++;
        }
        node.end ++;
    }

    public int search(String str) {
        if (null != str) {
            char[] chs = str.toCharArray();
            Node node = this.root;
            for (int i = 0; i < chs.length; i ++) {
                int index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }
        return 0;
    }

    public int prefixNum(String str) {
        if (null != str) {
            char[] chs = str.toCharArray();
            Node node = this.root;
            for (int i = 0; i < chs.length; i ++) {
                int index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
        return 0;
    }

    public void delete(String str) {
        if (this.search(str) != 0) {
            char[] chs = str.toCharArray();
            Node node = this.root;
            node.pass --;
            for (int i = 0; i < chs.length; i ++) {
                int index = chs[i] - 'a';
                if (-- node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end -- ;
        }
    }
}

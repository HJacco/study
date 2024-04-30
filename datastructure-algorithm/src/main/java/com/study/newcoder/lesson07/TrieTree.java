package com.study.newcoder.lesson07;

/**
 * 前缀树
 */
public class TrieTree {
    public Node root;

    public TrieTree(Node root) {
        this.root = new Node();
    }

    /**
     * 插入字符串
     * 允许插入空串
     */
    public void insert(String str) {
        if (null == str) {
            return;
        }
        char[] charsOfStr = str.toCharArray();
        Node node = this.root;
        node.pass ++;
        for (int i = 0; i < charsOfStr.length; i ++) {
            int index = charsOfStr[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new Node();
            }
            node = node.nexts[index];
            node.pass ++;
        }
        node.end ++;
    }

    /**
     * 查询字符串出现次数
     */
    public int search(String str) {
        if (null == str) {
            return 0;
        }
        char[] charsOfStr = str.toCharArray();
        Node current = this.root;
        for (int i = 0; i < charsOfStr.length; i ++) {
            int index = charsOfStr[i] - 'a';
            if (null == current.nexts[index]) {
                return 0;
            }
            current = current.nexts[index];
        }
        return current.end;
    }

    /**
     * 以str为前缀的字符串数
     */
    public int prefixNum(String str) {
        if (null == str) {
            return 0;
        }
        char[] charsOfStr = str.toCharArray();
        Node current = this.root;
        for (int i = 0; i < charsOfStr.length; i ++) {
            int index = charsOfStr[i] - 'a';
            if (null == current.nexts[index]) {
                return 0;
            }
        }
        return current.pass;
    }

    /**
     * 删除字符串
     */
    public void delete(String str) {
        // 先判断字符串是否存在
        if (search(str) != 0) {
            char[] charsOfStr = str.toCharArray();
            Node current = this.root;
            current.pass --;
            for (int i = 0; i < charsOfStr.length; i ++) {
                int index = charsOfStr[i] - 'a';
                if (-- current.nexts[index].pass == 0) {
                    current.nexts[index] = null;
                    return;
                }
                current = current.nexts[index];
            }
            current.end -- ;
        }
    }

    static class Node {
        /**
         * 经过当前节点的次数
         */
        public int pass;
        /**
         * 以当前节点结尾的字符串数
         */
        public int end;

        /**
         * 下一个节点
         * 假设字符仅仅是26个字母，可根据实际的数据情况选择数据结构： HashMap
         */
        public Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }
    }

}

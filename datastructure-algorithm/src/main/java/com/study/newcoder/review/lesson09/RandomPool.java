package com.study.newcoder.review.lesson09;

import java.util.HashMap;

public class RandomPool {
    public static class Pool<K> {
        public HashMap<K, Integer> keyIndexMap;
        public HashMap<Integer, K> indexMap;
        public int size;

        public Pool() {
            this.size = 0;
            this.keyIndexMap = new HashMap<>();
            this.indexMap = new HashMap<>();
        }

        public void insert(K key) {
            if (!this.keyIndexMap.containsKey(key)) {
                this.size ++;
                this.keyIndexMap.put(key, this.size);
                this.indexMap.put(this.size, key);
            }
        }

        public void delete(K key) {
            // 当前key移除， 并将最后的key填充到当前key的位置
            if (this.keyIndexMap.containsKey(key)) {
                Integer deletedIndex = this.keyIndexMap.get(key);

                K lastKey = this.indexMap.get(this.size);
                this.indexMap.put(deletedIndex, lastKey);
                this.indexMap.remove(this.size);

                keyIndexMap.remove(key);

                this.size --;
            }
        }

        public K getRandom() {
            if (this.size > 0) {
                Integer randomIndex = (int) (Math.random() * size + 1);
                return this.indexMap.get(randomIndex);
            }
            return null;
        }
    }
}

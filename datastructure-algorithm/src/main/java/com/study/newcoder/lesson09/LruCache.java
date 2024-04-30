package com.study.newcoder.lesson09;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache {
    static class Cache extends LinkedHashMap<Integer, Integer>{
        private Integer capa;

        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capa;
        }

        public Cache(int capacity) {
            super(capacity, 0.75F, true);
            this.capa = capacity;
        }
    }

    private Cache cache;

    public LruCache(int capacity) {

        cache = new Cache(capacity);
    }

    public int get(Integer key) {
        return cache.containsKey(key) ? cache.get(key) : -1;
    }

    public void put(Integer key, int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        Integer i = 1;
        LruCache cache = new LruCache(2);
        cache.put(i, 1);
        cache.put(2, 2);
        System.out.println(cache.get(i));
    }
}

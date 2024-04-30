package com.study.lesson4;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache {
    Cache cache;
    public LruCache(int capacity) {
        this.cache = new Cache(capacity);
    }

    public int get(int key) {
        return cache.get(key);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
    static class Cache extends LinkedHashMap<Integer, Integer> {
        int capacity;
        public Cache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        @Override
        public boolean removeEldestEntry(Map.Entry entry) {
            return this.size() > capacity;
        }
    }

    public static void main(String[] args) {
        LruCache c = new LruCache(2);
        c.put(1,1);
        c.put(2,2);
        System.out.println(c.get(1));
        c.put(3,3);
        c.get(2);
        c.put(4,4);
        c.get(1);
        c.get(3);
        c.get(4);
    }
}

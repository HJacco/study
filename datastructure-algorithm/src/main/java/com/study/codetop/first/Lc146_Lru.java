package com.study.codetop.first;

import java.util.LinkedHashMap;
import java.util.Map;

public class Lc146_Lru {
    static class Cache extends LinkedHashMap<Integer, Integer> {
        private Integer capacity;

        public Cache(Integer size) {
            super(size, 0.75F, true);
            this.capacity = size;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return this.size() > this.capacity;
        }
    }
}

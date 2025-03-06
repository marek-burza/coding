package leetcode.lc146_lru_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/ #medium
 */
public final class LC146LRUCache {
    private LC146LRUCache() {
    }

    private static class Node {
        private Node preceding = null;
        private Node following = null;
        protected int key;
        protected int value;

        Node(final int keyValue, final int valueValue) {
            key = keyValue;
            value = valueValue;
        }
    }

    public static class LRUCache {
        private int capacity;
        private Map<Integer, Node> lut = new HashMap<>();
        private Node head;
        private Node tail;

        LRUCache(final int capacityValue) {
            capacity = capacityValue;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.following = tail;
            tail.preceding = head;
        }

        /**
         * Get element from the LRU
         *
         * @param key Identifies the element
         * @return Value associated with the key
         */
        public int get(final int key) {
            int value = -1;
            if (lut.containsKey(key)) {
                value = remove(key);
                insert(key, value);
            }
            return value;
        }

        /**
         * Set element in the LRU
         *
         * @param key   Identifies the element
         * @param value Value to be associated with the key
         */
        public void set(final int key, final int value) {
            if (lut.containsKey(key)) {
                remove(key);
            } else {
                if (lut.size() >= capacity) {
                    remove(tail.preceding.key);
                }
            }
            insert(key, value);
        }

        private void insert(final int key, final int value) {
            Node node = new Node(key, value);
            node.preceding = head;
            node.following = head.following;
            node.preceding.following = node;
            node.following.preceding = node;
            lut.put(key, node);
        }

        private int remove(final int key) {
            Node node = lut.get(key);
            lut.remove(key);
            node.preceding.following = node.following;
            node.following.preceding = node.preceding;
            return node.value;
        }
    }
}
package leetcode.lc146_lru_cache;

import org.junit.jupiter.api.Test;

import leetcode.lc146_lru_cache.LC146LRUCache.LRUCache;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC146LRUCacheTests {
    @Test
    public void testExample() throws Exception {
        LRUCache cache = new LRUCache(2);
        cache.set(2, 1);
        cache.set(1, 1);
        assertEquals(1, cache.get(2));
        cache.set(4, 1);
        assertEquals(-1, cache.get(1));
        assertEquals(1, cache.get(2));
    }

    @Test
    public void testRepeatedPutSame() throws Exception {
        LRUCache cache = new LRUCache(2);
        cache.set(1, 1);
        cache.set(1, 1);
        assertEquals(1, cache.get(1));
    }
}

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
```rust
use std::collections::{HashMap, HashSet};

/// LeetCode problem: LRU Cache
///
/// https://leetcode.com/problems/lru-cache/
pub struct LRUCache {
    capacity: usize,
    key_value_map: HashMap<usize, (usize, usize)>,
    head: usize,
    tail: usize,
}

impl LRUCache {
    /// Constructor for LRU Cache
    ///
    /// # Arguments
    ///
    /// * `capacity` - The maximum number of elements the cache can hold
    pub fn new(capacity: usize) -> Self {
        LRUCache {
            capacity,
            key_value_map: HashMap::new(),
            head: 0,
            tail: 0,
        }
    }

    /// Get element from LRU
    ///
    /// # Arguments
    ///
    /// * `key` - The key of the element to retrieve
    ///
    /// # Returns
    ///
    /// The value associated with the given key, or -1 if not found
    pub fn get(&mut self, key: usize) -> i32 {
        if let Some((value, _)) = self.key_value_map.get(&key) {
            return *value;
        }
        return -1;
    }

    /// Set element in LRU
    ///
    /// # Arguments
    ///
    /// * `key` - The key of the element to set
    /// * `value` - The value associated with the given key
    pub fn set(&mut self, key: usize, value: i32) {
        if let Some((old_value, old_key)) = self.key_value_map.get_mut(&key) {
            self.remove(old_key);
        }
        if self.key_value_map.len() >= self.capacity {
            self.remove(self.tail);
        }
        self.insert(key, value);
    }

    /// Insert element into LRU
    fn insert(&mut self, key: usize, value: i32) {
        let node = (key, value);
        self.key_value_map.insert(key, node);

        // Update head and tail pointers
        if self.head == 0 || self.key_value_map.contains_key(&(self.head - 1)) {
            self.tail = self.tail - 1;
        }
    }

    /// Remove element from LRU
    fn remove(&mut self, key: usize) {
        let _ = self.key_value_map.remove(&key);
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut cache = LRUCache::new(2);
        cache.set(2, 1);
        cache.set(1, 1);
        assert_eq!(cache.get(2), 1);
        cache.set(4, 1);
        assert_eq!(cache.get(1), -1);
        assert_eq!(cache.get(2), 1);
    }

    #[test]
    fn test_repeated_put_same() {
        let mut cache = LRUCache::new(2);
        cache.set(1, 1);
        cache.set(1, 1);
        assert_eq!(cache.get(1), 1);
    }
}
```
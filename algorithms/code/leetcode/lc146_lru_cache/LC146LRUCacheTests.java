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

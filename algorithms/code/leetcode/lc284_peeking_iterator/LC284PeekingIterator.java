package leetcode.lc284_peeking_iterator;

import java.util.Iterator;

/**
 * https://leetcode.com/problems/peeking-iterator/
 * #medium
 */
public final class LC284PeekingIterator {
    public static class PeekingIterator implements Iterator<Integer> {
        private final Iterator<Integer> iterator;
        private boolean got;
        private int value;

        PeekingIterator(final Iterator<Integer> iteratorValue) {
            iterator = iteratorValue;
            got = false;
        }

        public final Integer peek() {
            if (!got) {
                got = true;
                value = iterator.next();
            }
            return value;
        }

        @Override
        public final Integer next() {
            if (got) {
                got = false;
            } else {
                value = iterator.next();
            }
            return value;
        }

        @Override
        public final boolean hasNext() {
            return got || iterator.hasNext();
        }
    }
}
package leetcode.lc284_peeking_iterator;

import org.junit.jupiter.api.Test;

import leetcode.lc284_peeking_iterator.LC284PeekingIterator.PeekingIterator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC284PeekingIteratorTests {
    @Test
    public void testExample() throws Exception {
        Integer[] values = new Integer[] { 1, 2, 3 };
        PeekingIterator peeking = new PeekingIterator(Arrays.asList(values).iterator());
        assertEquals(1, peeking.next().intValue());
        assertEquals(2, peeking.peek().intValue());
        assertEquals(2, peeking.next().intValue());
        assertEquals(3, peeking.next().intValue());
        assertFalse(peeking.hasNext());
    }

    @Test
    public void testNothing() throws Exception {
        assertNotNull(new LC284PeekingIterator());
    }

    @Test
    public void testOther() throws Exception {
        Integer[] values = new Integer[] { 1, 2, 3, 4 };
        PeekingIterator peeking = new PeekingIterator(Arrays.asList(values).iterator());
        assertTrue(peeking.hasNext());
        assertEquals(1, peeking.peek().intValue());
        assertEquals(1, peeking.peek().intValue());
        assertEquals(1, peeking.next().intValue());
        assertEquals(2, peeking.next().intValue());
        assertEquals(3, peeking.peek().intValue());
        assertEquals(3, peeking.peek().intValue());
        assertEquals(3, peeking.next().intValue());
        assertTrue(peeking.hasNext());
        assertEquals(4, peeking.peek().intValue());
        assertTrue(peeking.hasNext());
        assertEquals(4, peeking.next().intValue());
        assertFalse(peeking.hasNext());
    }
}

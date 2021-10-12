package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/peeking-iterator/
 * #medium
 */
public class LC284PeekingIterator {
    class PeekingIterator implements Iterator<Integer> {
        private final Iterator<Integer> iterator;
        private boolean got;
        private int value;

        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
            got = false;
        }

        public Integer peek() {
            if (!got) {
                got = true;
                value = iterator.next();
            }
            return value;
        }

        @Override
        public Integer next() {
            if (got) {
                got = false;
            } else {
                value = iterator.next();
            }
            return value;
        }

        @Override
        public boolean hasNext() {
            return got || iterator.hasNext();
        }
    }

    @Test
    public void test_example() throws Exception {
        Integer[] values = new Integer[]{1, 2, 3};
        PeekingIterator peeking = new PeekingIterator(Arrays.asList(values).iterator());
        assertEquals(1, peeking.next().intValue());
        assertEquals(2, peeking.peek().intValue());
        assertEquals(2, peeking.next().intValue());
        assertEquals(3, peeking.next().intValue());
        assertEquals(false, peeking.hasNext());
    }
}

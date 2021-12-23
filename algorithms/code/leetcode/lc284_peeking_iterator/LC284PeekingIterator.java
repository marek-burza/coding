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

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
```rust
pub struct PeekingIterator<I> {
    iterator: I,
    got: bool,
    value: Option<i32>,
}

impl<I: Iterator<Item = i32>> PeekingIterator<I> {
    pub fn new(iterator: I) -> Self {
        Self {
            iterator,
            got: false,
            value: None,
        }
    }

    pub fn peek(&mut self) -> Option<i32> {
        if !self.got {
            self.got = true;
            self.value = Some(self.iterator.next().unwrap());
        }
        self.value
    }

    pub fn next(&mut self) -> i32 {
        if self.got {
            self.got = false;
        } else {
            self.value = Some(self.iterator.next().unwrap());
        }
        self.value.unwrap()
    }

    pub fn has_next(&self) -> bool {
        self.got || self.iterator.hasNext()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut peeking = PeekingIterator::new(std::iter::repeat(1).take(3).collect::<Vec<_>>().into_iter());
        assert_eq!(peeking.next(), 1);
        assert_eq!(peeking.peek(), Some(2));
        assert_eq!(peeking.next(), 2);
        assert_eq!(peeking.next(), 3);
        assert!(!peeking.has_next());
    }

    #[test]
    fn test_nothing() {
        let _ = PeekingIterator::new(std::iter::empty::<i32>());
    }

    #[test]
    fn test_other() {
        let mut peeking = PeekingIterator::new(std::iter::repeat(1).take(4).collect::<Vec<_>>().into_iter());
        assert!(peeking.has_next());
        assert_eq!(peeking.peek(), Some(1));
        assert_eq!(peeking.peek(), Some(1));
        assert_eq!(peeking.next(), 1);
        assert_eq!(peeking.next(), 2);
        assert_eq!(peeking.peek(), Some(3));
        assert_eq!(peeking.peek(), Some(3));
        assert_eq!(peeking.next(), 3);
        assert!(peeking.has_next());
        assert_eq!(peeking.peek(), Some(4));
        assert!(peeking.has_next());
        assert_eq!(peeking.next(), 4);
        assert!(!peeking.has_next());
    }
}
```
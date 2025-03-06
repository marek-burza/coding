package leetcode.lc341_flatten_nested_list_iterator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * #medium
 */
public final class LC341FlattenNestedListIterator {
    public static class NestedInteger {
        public Integer integerObject = null;
        public List<NestedInteger> listObject = null;

        NestedInteger(final Integer object) {
            integerObject = object;
        }

        NestedInteger(final List<NestedInteger> object) {
            listObject = object;
        }

        public final boolean isInteger() {
            return integerObject != null;
        }

        public final Integer getInteger() {
            return integerObject;
        }

        public final List<NestedInteger> getList() {
            return listObject;
        }
    }

    public static class NestedIterator implements Iterator<Integer> {
        private static class Item {
            public NestedInteger item;
            public int skip;

            Item(final Integer object, final int skipValue) {
                item = new NestedInteger(object);
                skip = skipValue;
            }

            Item(final List<NestedInteger> object, final int skipValue) {
                item = new NestedInteger(object);
                skip = skipValue;
            }
        }

        private Stack<Item> stack = new Stack<>();

        public NestedIterator(final List<NestedInteger> nestedList) {
            stack.push(new Item(nestedList, 0));
        }

        private Item objectify(final NestedInteger nested, final int skip) {
            if (nested.isInteger()) {
                return new Item(nested.getInteger(), skip);
            } else {
                return new Item(nested.getList(), skip);
            }
        }

        private void find() {
            if (stack.isEmpty()) {
                return;
            }
            while (!stack.isEmpty() && !stack.peek().item.isInteger()) {
                Item top = stack.peek();
                List<NestedInteger> list = top.item.getList();
                if (list.size() <= top.skip) {
                    stack.pop();
                    continue;
                }
                stack.push(objectify(list.get(top.skip), 0));
                top.skip++;
            }
        }

        @Override
        public final Integer next() {
            if (hasNext()) {
                return stack.pop().item.getInteger();
            } else {
                return null;
            }
        }

        @Override
        public final boolean hasNext() {
            find();
            return !stack.isEmpty();
        }
    }
}
package leetcode.lc341_flatten_nested_list_iterator;

import org.junit.jupiter.api.Test;

import leetcode.lc341_flatten_nested_list_iterator.LC341FlattenNestedListIterator.NestedInteger;
import leetcode.lc341_flatten_nested_list_iterator.LC341FlattenNestedListIterator.NestedIterator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC341FlattenNestedListIteratorTests {
    private void generic(final List<NestedInteger> list, final int[] expected) throws Exception {
        NestedIterator nested = new NestedIterator(list);
        for (int value : expected) {
            assertTrue(nested.hasNext());
            assertEquals(value, nested.next().intValue());
        }
        assertFalse(nested.hasNext());
        assertEquals(null, nested.next());
    }

    @Test
    public void testExample1() throws Exception {
        List<NestedInteger> list11a = new ArrayList<>();
        list11a.add(new NestedInteger(1));
        list11a.add(new NestedInteger(1));
        List<NestedInteger> list11b = new ArrayList<>();
        list11b.add(new NestedInteger(1));
        list11b.add(new NestedInteger(1));
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestedInteger(list11a));
        list.add(new NestedInteger(2));
        list.add(new NestedInteger(list11b));
        int[] expected = { 1, 1, 2, 1, 1 };
        generic(list, expected);
    }

    @Test
    public void testExample2() throws Exception {
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestedInteger(1));
        List<NestedInteger> list4 = new ArrayList<>();
        list4.add(new NestedInteger(4));
        List<NestedInteger> list6 = new ArrayList<>();
        list6.add(new NestedInteger(6));
        list4.add(new NestedInteger(list6));
        list.add(new NestedInteger(list4));
        int[] expected = { 1, 4, 6 };
        generic(list, expected);
    }

    @Test
    public void testNothing() throws Exception {
        assertNotNull(new LC341FlattenNestedListIterator());
    }
}

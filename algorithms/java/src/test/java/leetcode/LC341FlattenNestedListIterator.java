package leetcode;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * #medium
 */
public class LC341FlattenNestedListIterator {
    public class NestedInteger {
        private Object object;

        public NestedInteger(Object object) {
            this.object = object;
        }

        public boolean isInteger() {
            return object instanceof Integer;
        }

        public Integer getInteger() {
            return (Integer) object;
        }

        public List<NestedInteger> getList() {
            return (List<NestedInteger>) object;
        }

        public String toString() {
            if (object instanceof Integer) {
                return object.toString();
            } else {
                List<NestedInteger> list = (List<NestedInteger>) object;
                StringBuilder builder = new StringBuilder();
                builder.append("(");
                for (NestedInteger nested : list) {
                    builder.append(nested.toString());
                }
                builder.append(")");
                return builder.toString();
            }
        }
    }

    public class NestedIterator implements Iterator<Integer> {
        private class Item {
            public Object item;
            public int skip;

            public Item(Object item, int skip) {
                this.item = item;
                this.skip = skip;
            }

            public String toString() {
                return String.format("item: %s; skip: %d", item, skip);
            }
        }

        private Stack<Item> stack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            stack.push(new Item(nestedList, 0));
        }

        private Object objectify(NestedInteger nested) {
            if (nested.isInteger()) {
                return nested.getInteger();
            } else {
                return nested.getList();
            }
        }

        private void find() {
            if (stack.isEmpty()) return;
            while (!stack.isEmpty() && !(stack.peek().item instanceof Integer)) {
                Item top = stack.peek();
                List<NestedInteger> list = (List<NestedInteger>) top.item;
                if (list.size() <= top.skip) {
                    stack.pop();
                    continue;
                }
                stack.push(new Item(objectify(list.get(top.skip)), 0));
                top.skip++;
            }
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                return (Integer) stack.pop().item;
            } else {
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            find();
            return !stack.isEmpty();
        }
    }

    @Test
    public void test_example_1() throws Exception {
        List<NestedInteger> list_1_1_a = new ArrayList<>();
        list_1_1_a.add(new NestedInteger(1));
        list_1_1_a.add(new NestedInteger(1));
        List<NestedInteger> list_1_1_b = new ArrayList<>();
        list_1_1_b.add(new NestedInteger(1));
        list_1_1_b.add(new NestedInteger(1));
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestedInteger(list_1_1_a));
        list.add(new NestedInteger(2));
        list.add(new NestedInteger(list_1_1_b));
        int[] expected = {1, 1, 2, 1, 1};
        NestedIterator nested = new NestedIterator(list);
        for (int value : expected) {
            assertEquals(true, nested.hasNext());
            assertEquals(value, nested.next().intValue());
        }
        assertEquals(false, nested.hasNext());
        assertEquals(null, nested.next());
    }

    @Test
    public void test_example_2() throws Exception {
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestedInteger(1));
        List<NestedInteger> list_4 = new ArrayList<>();
        list_4.add(new NestedInteger(4));
        List<NestedInteger> list_6 = new ArrayList<>();
        list_6.add(new NestedInteger(6));
        list_4.add(new NestedInteger(list_6));
        list.add(new NestedInteger(list_4));
        int[] expected = {1, 4, 6};
        NestedIterator nested = new NestedIterator(list);
        for (int value : expected) {
            assertEquals(true, nested.hasNext());
            assertEquals(value, nested.next().intValue());
        }
        assertEquals(false, nested.hasNext());
        assertEquals(null, nested.next());
    }
}

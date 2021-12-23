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

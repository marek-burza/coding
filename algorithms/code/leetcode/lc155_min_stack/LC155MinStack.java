package leetcode.lc155_min_stack;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/ #easy
 */
public final class LC155MinStack {
    private LC155MinStack() {
    }

    public static final class MinStack {
        private Stack<Integer> stack = new Stack<>();
        private PriorityQueue<Integer> heap = new PriorityQueue<>();

        public void push(final int x) {
            stack.push(x);
            heap.add(x);
        }

        public void pop() {
            heap.remove(stack.pop());
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return heap.peek();
        }
    }
}

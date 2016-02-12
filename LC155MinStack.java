package leetcode;

import java.util.PriorityQueue;
import java.util.Stack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/min-stack/
 */
public class LC155MinStack {
    public class MinStack {
        private Stack<Integer> stack = new Stack<>();
        private PriorityQueue<Integer> heap = new PriorityQueue<>();

        public void push(int x) {
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

    @Test
    public void test_example() throws Exception {
        MinStack solution = new MinStack();
        solution.push(5);
        assertEquals(5, solution.getMin());
        solution.push(4);
        assertEquals(4, solution.getMin());
        solution.pop();
        assertEquals(5, solution.getMin());
        solution.push(3);
        assertEquals(3, solution.getMin());
        solution.top();
        assertEquals(3, solution.getMin());
        solution.push(2);
        assertEquals(2, solution.getMin());
        solution.push(1);
        assertEquals(1, solution.getMin());
    }
}

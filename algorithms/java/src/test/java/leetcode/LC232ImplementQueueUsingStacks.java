package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/
 * #easy
 */
public class LC232ImplementQueueUsingStacks {
    class MyQueue {
        private Stack<Integer> stack = new Stack();
        private Stack<Integer> buffer = new Stack();

        // Push element x to the back of queue.
        public void push(int x) {
            while (stack.size() != 0) {
                buffer.push(stack.pop());
            }
            stack.push(x);
            while (buffer.size() != 0) {
                stack.push(buffer.pop());
            }
        }

        // Removes the element from in front of queue.
        public void pop() {
            stack.pop();
        }

        // Get the front element.
        public int peek() {
            return stack.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return stack.size() == 0;
        }
    }

    @Test
    public void test_something() throws Exception {
        MyQueue queue = new MyQueue();
        for (int i = 0; i < 6; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 6; i++) {
            assertEquals(false, queue.empty());
            assertEquals(i, queue.peek());
            queue.pop();
        }
        assertEquals(true, queue.empty());
    }
}

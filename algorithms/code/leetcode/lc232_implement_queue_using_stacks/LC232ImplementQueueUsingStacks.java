package leetcode.lc232_implement_queue_using_stacks;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/ #easy
 */
public final class LC232ImplementQueueUsingStacks {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> buffer = new Stack<>();

    // Push element x to the back of queue.
    public void push(final int x) {
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
package leetcode.lc232_implement_queue_using_stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC232ImplementQueueUsingStacksTests {
    @Test
    public void testSomething() throws Exception {
        LC232ImplementQueueUsingStacks queue = new LC232ImplementQueueUsingStacks();
        for (int i = 0; i < 6; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 6; i++) {
            assertFalse(queue.empty());
            assertEquals(i, queue.peek());
            queue.pop();
        }
        assertTrue(queue.empty());
    }
}

package leetcode.lc225_implement_stack_using_queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/ #easy
 */
public final class LC225ImplementStackUsingQueues {
    private Queue<Integer> active = new LinkedList<>();
    private Queue<Integer> other = new LinkedList<>();

    private void swap() {
        Queue<Integer> swap = other;
        other = active;
        active = swap;
    }

    // Push element x onto stack.
    public void push(final int x) {
        // 1 - active
        other.add(x);
        while (active.size() > 0) { // ! empty
            other.add(active.poll());
        }
        swap();
    }

    // Removes the element on top of the stack.
    public void pop() {
        active.poll();
    }

    // Get the top element.
    public int top() {
        return active.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return active.isEmpty();
    }

    // It's also possible with just one stack
}
package leetcode.lc225_implement_stack_using_queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC225ImplementStackUsingQueuesTests {
    @Test
    public void testExample() throws Exception {
        LC225ImplementStackUsingQueues solution = new LC225ImplementStackUsingQueues();
        solution.push(5);
        solution.push(2);
        assertEquals(2, solution.top());
        solution.pop();
        assertEquals(5, solution.top());
        assertFalse(solution.empty());
        solution.pop();
        assertTrue(solution.empty());
    }
}

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/
 * #easy
 */
public class LC225ImplementStackUsingQueues {
    public class Solution {
        private Queue<Integer> active = new LinkedList<>();
        private Queue<Integer> other = new LinkedList<>();

        private void swap() {
            Queue<Integer> swap = other;
            other = active;
            active = swap;
        }

        // Push element x onto stack.
        public void push(int x) {
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
    }

    // It's also possible with just one stack

    @Test
    public void test_example() throws Exception {
        Solution solution = new Solution();
        solution.push(5);
        solution.push(2);
        assertEquals(2, solution.top());
        solution.pop();
        assertEquals(5, solution.top());
        assertEquals(false, solution.empty());
        solution.pop();
        assertEquals(true, solution.empty());
    }
}

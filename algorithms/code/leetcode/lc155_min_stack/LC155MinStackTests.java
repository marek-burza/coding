package leetcode.lc155_min_stack;

import org.junit.jupiter.api.Test;

import leetcode.lc155_min_stack.LC155MinStack.MinStack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC155MinStackTests {
    @Test
    public void testExample() throws Exception {
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

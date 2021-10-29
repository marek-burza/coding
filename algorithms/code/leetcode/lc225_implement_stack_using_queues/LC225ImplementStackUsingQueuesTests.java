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

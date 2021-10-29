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

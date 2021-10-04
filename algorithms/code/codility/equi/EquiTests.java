package codility.equi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * http://blog.codility.com/2011/03/solutions-for-task-equi.html
 */
public class EquiTests {
    @Test
    public void test() throws Exception {
        assertEquals(1, new Equi().findEquilibriumIndex(new int[] { -1, 3, -4, 5, 1, -6, 2, 1 }));
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals(-1, new Equi().findEquilibriumIndex(new int[] {}));
    }

    @Test
    public void testInvalid() throws Exception {
        assertEquals(-1, new Equi().findEquilibriumIndex(new int[] { 1, 2, 3, 4 }));
    }
}

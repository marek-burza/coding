package leetcode.lc319_bulb_switcher;

/**
 * https://leetcode.com/problems/bulb-switcher/
 * #medium
 */
public final class LC319BulbSwitcher {
    public int bulbSwitch(final int n) {
        return (int) Math.sqrt(n);
    }
}
package leetcode.lc319_bulb_switcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC319BulbSwitcherTests {
    @Test
    public void test1To16() throws Exception {
        int[] expected = { 0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4 };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], new LC319BulbSwitcher().bulbSwitch(i));
        }
    }
}
```rust
mod leetcode_lc319_bulb_switcher {
    pub mod lc319_bulb_switcher {
        /// https://leetcode.com/problems/bulb-switcher/
        /// #medium

        pub fn bulb_switch(n: i32) -> i32 {
            ((n as f64).sqrt() as i32)
        }
    }

    pub mod lc319_bulb_switcher_tests {
        use super::lc319_bulb_switcher;

        #[test]
        pub fn test1_to_16() {
            let expected = [0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3];
            for i in 0..expected.len() {
                assert_eq!(expected[i], lc319_bulb_switcher::bulb_switch(i as i32));
            }
        }
    }
}
```
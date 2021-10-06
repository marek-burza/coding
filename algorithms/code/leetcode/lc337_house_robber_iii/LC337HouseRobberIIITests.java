package leetcode.lc337_house_robber_iii;

import org.junit.jupiter.api.Test;

import leetcode.lc337_house_robber_iii.LC337HouseRobberIII.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC337HouseRobberIIITests {
    @Test
    public void testExample1() throws Exception {
        TreeNode t3 = new TreeNode(3);
        TreeNode l2 = new TreeNode(2);
        TreeNode lr3 = new TreeNode(3);
        TreeNode r3 = new TreeNode(3);
        TreeNode rr1 = new TreeNode(1);
        t3.left = l2;
        t3.right = r3;
        t3.left.right = lr3;
        t3.right.right = rr1;
        assertEquals(7, new LC337HouseRobberIII().rob(t3));
    }

    @Test
    public void testExample2() throws Exception {
        TreeNode t3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode ll1 = new TreeNode(1);
        TreeNode lr3 = new TreeNode(3);
        TreeNode r5 = new TreeNode(5);
        TreeNode rr1 = new TreeNode(1);
        t3.left = l4;
        t3.right = r5;
        t3.left.left = ll1;
        t3.left.right = lr3;
        t3.right.right = rr1;
        assertEquals(9, new LC337HouseRobberIII().rob(t3));
    }
}

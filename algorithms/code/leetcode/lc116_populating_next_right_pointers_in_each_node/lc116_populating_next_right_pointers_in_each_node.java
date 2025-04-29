package leetcode.lc116_populating_next_right_pointers_in_each_node;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * #medium
 */
public final class LC116PopulatingNextRightPointersInEachNode {
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;

        TreeLinkNode(final int x) {
            val = x;
        }
    }

    public void connect(final TreeLinkNode root) {
        List<TreeLinkNode> current = new ArrayList<>();
        if (root != null) {
            current.add(root);
        }
        while (current.size() != 0) {
            List<TreeLinkNode> future = new ArrayList<>();
            int size = current.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = current.get(i);
                if (node.left != null) {
                    future.add(node.left);
                }
                if (node.right != null) {
                    future.add(node.right);
                }
                if (i < size - 1) {
                    node.next = current.get(i + 1);
                }
            }
            current = future;
        }
    }
}
package leetcode.lc116_populating_next_right_pointers_in_each_node;

import org.junit.jupiter.api.Test;

import leetcode.lc116_populating_next_right_pointers_in_each_node.LC116PopulatingNextRightPointersInEachNode.TreeLinkNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC116PopulatingNextRightPointersInEachNodeTests {
    @Test
    public void testEmpty() throws Exception {
        new LC116PopulatingNextRightPointersInEachNode().connect(null);
    }

    @Test
    public void testExample() throws Exception {
        TreeLinkNode n4 = new TreeLinkNode(4);
        TreeLinkNode n5 = new TreeLinkNode(5);
        TreeLinkNode n6 = new TreeLinkNode(6);
        TreeLinkNode n7 = new TreeLinkNode(7);
        TreeLinkNode n2 = new TreeLinkNode(2);
        n2.left = n4;
        n2.right = n5;
        TreeLinkNode n3 = new TreeLinkNode(3);
        n3.left = n6;
        n3.right = n7;
        TreeLinkNode n1 = new TreeLinkNode(1);
        n1.left = n2;
        n1.right = n3;

        new LC116PopulatingNextRightPointersInEachNode().connect(n1);

        assertEquals(1, n1.val);
        assertEquals(null, n1.next);
        assertEquals(n2, n1.left);
        assertEquals(n3, n1.right);

        assertEquals(2, n2.val);
        assertEquals(n3, n2.next);
        assertEquals(n4, n2.left);
        assertEquals(n5, n2.right);

        assertEquals(3, n3.val);
        assertEquals(null, n3.next);
        assertEquals(n6, n3.left);
        assertEquals(n7, n3.right);

        assertEquals(4, n4.val);
        assertEquals(n5, n4.next);
        assertEquals(null, n4.left);
        assertEquals(null, n4.right);

        assertEquals(5, n5.val);
        assertEquals(n6, n5.next);
        assertEquals(null, n5.left);
        assertEquals(null, n5.right);

        assertEquals(6, n6.val);
        assertEquals(n7, n6.next);
        assertEquals(null, n6.left);
        assertEquals(null, n6.right);

        assertEquals(7, n7.val);
        assertEquals(null, n7.next);
        assertEquals(null, n7.left);
        assertEquals(null, n7.right);
    }
}
```rust
// Define the TreeLinkNode struct
#[derive(Debug)]
struct TreeLinkNode {
    val: i32,
    left: Option<Box<TreeLinkNode>>,
    right: Option<Box<TreeLinkNode>>,
    next: Option<Box<TreeLinkNode>>,
}

impl TreeLinkNode {
    // Create a new TreeLinkNode with the given value
    fn new(val: i32) -> Self {
        TreeLinkNode {
            val,
            left: None,
            right: None,
            next: None,
        }
    }

    // Connect the nodes in the binary tree
    fn connect(&mut self, root: &TreeLinkNode) {
        let mut current = vec![];
        if root != None {
            current.push(root);
        }
        while current.len() != 0 {
            let future = vec![];
            for i in 0..current.len() {
                let node = current[i];
                if node.left.is_some() {
                    future.push(node.left.clone());
                }
                if node.right.is_some() {
                    future.push(node.right.clone());
                }
                if i < current.len() - 1 {
                    node.next = current[i + 1].clone();
                }
            }
            current = future;
        }
    }
}

// Define the tests for TreeLinkNode
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        let mut tree: TreeLinkNode = TreeLinkNode::new(0);
        tree.connect(&None);
        // Implement test for empty case
    }

    #[test]
    fn test_example() {
        let n4 = TreeLinkNode::new(4);
        let n5 = TreeLinkNode::new(5);
        let n6 = TreeLinkNode::new(6);
        let n7 = TreeLinkNode::new(7);
        let n2 = TreeLinkNode::new(2);
        n2.left = Some(n4.clone());
        n2.right = Some(n5.clone());
        let n3 = TreeLinkNode::new(3);
        n3.left = Some(n6.clone());
        n3.right = Some(n7.clone());
        let n1 = TreeLinkNode::new(1);
        n1.left = Some(n2.clone());
        n1.right = Some(n3.clone());

        n1.connect(&n4);

        assert_eq!(1, n1.val);
        assert!(n1.next.is_none());

        assert_eq!(Some(n2), n1.left);
        assert_eq!(Some(n3), n1.right);

        assert_eq!(2, n2.val);
        assert_eq!(Some(n3), n2.next);

        assert_eq!(None, n2.left);
        assert_eq!(Some(n4), n2.right);

        assert_eq!(3, n3.val);
        assert!(n3.next.is_none());

        assert_eq!(Some(n6), n3.left);
        assert_eq!(Some(n7), n3.right);

        assert_eq!(4, n4.val);
        assert_eq!(Some(n5), n4.next);

        assert!(n4.left.is_none());
        assert!(n4.right.is_none());

        assert_eq!(5, n5.val);
        assert_eq!(Some(n6), n5.next);

        assert!(n5.left.is_none());
        assert!(n5.right.is_none());

        assert_eq!(6, n6.val);
        assert_eq!(Some(n7), n6.next);

        assert!(n6.left.is_none());
        assert!(n6.right.is_none());

        assert_eq!(7, n7.val);
        assert!(n7.next.is_none());
    }
}
```
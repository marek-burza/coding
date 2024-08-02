package lc024

// https://leetcode.com/problems/swap-nodes-in-pairs/

// ListNode defines a singly-linked list
type ListNode struct {
    Val int
    Next *ListNode
}

// SwapPairs swaps every two adjacent nodes in the linked list
func SwapPairs(head *ListNode) *ListNode {
	dummy := &ListNode{Val: 0, Next: head}
	current := dummy
	for current.Next != nil && current.Next.Next != nil {
		first := current.Next
		second := current.Next.Next
		after := current.Next.Next.Next
		current.Next = second
		second.Next = first
		first.Next = after
		current = current.Next.Next
	}
	return dummy.Next
}

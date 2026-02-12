package lc019

import (
	"testing"
)

func Test12And11(t *testing.T) {
	n1 := &ListNode{Val: 1}
	n2 := &ListNode{Val: 2}
	n1.Next = n2
	n := n1
	if n.Val != 1 {
		t.Errorf("RemoveNthFromEnd - Expected 1, but got %d!", n.Val)
	}
	if n.Next.Val != 2 {
		t.Errorf("RemoveNthFromEnd - Expected 2, but got %d!", n.Next.Val)
	}
	n = removeNthFromEnd(n, 1)
	if n.Val != 1 {
		t.Errorf("RemoveNthFromEnd - Expected 1, but got %d!", n.Val)
	}
	n = removeNthFromEnd(n, 1)
	if n != nil {
		t.Errorf("RemoveNthFromEnd - Expected nil, but got %v!", n)
	}
}

func TestNull0(t *testing.T) {
	n := removeNthFromEnd(nil, 0)
	if n != nil {
		t.Errorf("RemoveNthFromEnd - Expected nil, but got %v!", n)
	}
}

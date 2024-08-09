package lc021

import (
	"testing"
)

func generic(t *testing.T, n0 *ListNode) {
	if n0.Val != 1 {
		t.Errorf("MergeTwoLists - Expected 1, but got %d!", n0.Val)
	}
	if n0.Next.Val != 2 {
		t.Errorf("MergeTwoLists - Expected 2, but got %d!", n0.Next.Val)
	}
	if n0.Next.Next.Val != 3 {
		t.Errorf("MergeTwoLists - Expected 3, but got %d!", n0.Next.Next.Val)
	}
	if n0.Next.Next.Next.Val != 4 {
		t.Errorf("MergeTwoLists - Expected 4, but got %d!", n0.Next.Next.Next.Val)
	}
	if n0.Next.Next.Next.Next.Val != 5 {
		t.Errorf("MergeTwoLists - Expected 5, but got %d!", n0.Next.Next.Next.Next.Val)
	}
	if n0.Next.Next.Next.Next.Next.Val != 6 {
		t.Errorf("MergeTwoLists - Expected 6, but got %d!", n0.Next.Next.Next.Next.Next.Val)
	}
}

func Test13579And246(t *testing.T) {
	n9 := &ListNode{Val: 9}
	n7 := &ListNode{Val: 7, Next: n9}
	n5 := &ListNode{Val: 5, Next: n7}
	n3 := &ListNode{Val: 3, Next: n5}
	n1 := &ListNode{Val: 1, Next: n3}
	n6 := &ListNode{Val: 6}
	n4 := &ListNode{Val: 4, Next: n6}
	n2 := &ListNode{Val: 2, Next: n4}
	n0 := mergeTwoLists(n1, n2)
	generic(t, n0)
	if n0.Next.Next.Next.Next.Next.Next.Val != 7 {
		t.Errorf("MergeTwoLists - Expected 7, but got %d!", n0.Next.Next.Next.Next.Next.Next.Val)
	}
	if n0.Next.Next.Next.Next.Next.Next.Next.Val != 9 {
		t.Errorf("MergeTwoLists - Expected 9, but got %d!", n0.Next.Next.Next.Next.Next.Next.Next.Val)
	}
}

func Test123And456(t *testing.T) {
	n3 := &ListNode{Val: 3}
	n2 := &ListNode{Val: 2, Next: n3}
	n1 := &ListNode{Val: 1, Next: n2}
	n6 := &ListNode{Val: 6}
	n5 := &ListNode{Val: 5, Next: n6}
	n4 := &ListNode{Val: 4, Next: n5}
	n0 := mergeTwoLists(n1, n4)
	generic(t, n0)
	if n0.Next.Next.Next.Next.Next.Next != nil {
		t.Errorf("MergeTwoLists - Expected nil, but got %d!", n0.Next.Next.Next.Next.Next.Next.Val)
	}
}

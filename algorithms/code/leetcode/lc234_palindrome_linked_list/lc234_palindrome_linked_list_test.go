package lc234

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsPalindrome - Expected %v, got %v!", expected, result)
	}
}

func TestPalindromeOdd(t *testing.T) {
	listed := &ListNode{Val: 0}
	listed.Next = &ListNode{Val: 1}
	listed.Next.Next = &ListNode{Val: 2}
	listed.Next.Next.Next = &ListNode{Val: 1}
	listed.Next.Next.Next.Next = &ListNode{Val: 0}
	generic(t, isPalindrome(listed), true)
}

func TestPalindromeEven(t *testing.T) {
	listed := &ListNode{Val: 0}
	listed.Next = &ListNode{Val: 1}
	listed.Next.Next = &ListNode{Val: 1}
	listed.Next.Next.Next = &ListNode{Val: 0}
	generic(t, isPalindrome(listed), true)
}

func TestNotPalindromeOdd(t *testing.T) {
	listed := &ListNode{Val: 0}
	listed.Next = &ListNode{Val: 1}
	listed.Next.Next = &ListNode{Val: 2}
	listed.Next.Next.Next = &ListNode{Val: 8}
	listed.Next.Next.Next.Next = &ListNode{Val: 0}
	generic(t, isPalindrome(listed), false)
}

func TestNotPalindromeEven(t *testing.T) {
	listed := &ListNode{Val: 0}
	listed.Next = &ListNode{Val: 1}
	listed.Next.Next = &ListNode{Val: 8}
	listed.Next.Next.Next = &ListNode{Val: 0}
	generic(t, isPalindrome(listed), false)
}

func TestEmpty(t *testing.T) {
	generic(t, isPalindrome(nil), true)
}

func TestSingle(t *testing.T) {
	generic(t, isPalindrome(&ListNode{Val: 0}), true)
}

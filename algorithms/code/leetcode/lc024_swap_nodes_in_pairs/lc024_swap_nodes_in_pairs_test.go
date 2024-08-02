package lc024

import (
	"testing"
)

func generic(t *testing.T, head *ListNode, expected []int) {
    result := SwapPairs(head)
    for _, value := range expected {    
	    if result == nil {
		    t.Errorf("SwapPairs - Unexpected nil!")
	    }
        if value != result.Val {
            t.Errorf("SwapPairs - Expected %v, got %v!", value, result.Val)
        }
        result = result.Next
    }
}

func TestExample(t *testing.T) {
    n1 := &ListNode{Val: 1}
    n2 := &ListNode{Val: 2}
    n3 := &ListNode{Val: 3}
    n4 := &ListNode{Val: 4}
    n1.Next = n2
    n2.Next = n3
    n3.Next = n4
    expected := []int{2, 1, 4, 3}
    generic(t, n1, expected)
}

func TestExampleImpaired(t *testing.T) {
    n1 := &ListNode{Val: 1}
    n2 := &ListNode{Val: 2}
    n3 := &ListNode{Val: 3}
    n1.Next = n2
    n2.Next = n3
    expected := []int{2, 1, 3}
    generic(t, n1, expected)
}

package lc382

import (
	"errors"
	"testing"
)

func TestExample(t *testing.T) {
	head := &ListNode{Val: 1}
	head.Next = &ListNode{Val: 2}
	head.Next.Next = &ListNode{Val: 3}
	counts := make([]int, 3)
	solution := NewSolution(head)
	for range 10000 {
		value, err := solution.GetRandom()
		if err != nil {
			t.Errorf("GetRandom - Expected no error, got %v!", err)
			return
		}
		if value < 1 || value > 3 {
			t.Errorf("GetRandom - Expected a value between 1 and 3, got %v!", value)
		}
		counts[value-1]++
	}
	for i, count := range counts {
		if count/1000 != 3 {
			t.Errorf("GetRandom - Expected about a third of the picks for %v, got %v!", i+1, count)
		}
	}
	// Should use Chi-squared test
}

func TestNothing(t *testing.T) {
	solution := NewSolution(nil)
	_, err := solution.GetRandom()
	if !errors.Is(err, ErrEmptyList) {
		t.Errorf("GetRandom - Expected an empty list error, got %v!", err)
	}
}

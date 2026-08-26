package lc384

import (
	"reflect"
	"slices"
	"testing"
)

func generic(t *testing.T, nums []int) {
	solution := NewSolution(append([]int{}, nums...))
	result := solution.Shuffle()
	reset := solution.Reset()
	if !reflect.DeepEqual(nums, reset) {
		t.Errorf("Reset - Expected %v, got %v!", nums, reset)
	}
	slices.Sort(nums)
	slices.Sort(result)
	if !reflect.DeepEqual(nums, result) {
		t.Errorf("Shuffle - Expected %v, got %v!", nums, result)
	}
}

func TestExample(t *testing.T) {
	nums := []int{1, 2, 3}
	generic(t, nums)
	// Should use Chi-squared test
}

func TestNothing(t *testing.T) {
	solution := NewSolution([]int{})
	if len(solution.Shuffle()) != 0 {
		t.Errorf("Shuffle - Expected nothing!")
	}
}

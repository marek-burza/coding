package lc215

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("FindKthLargest - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	nums := []int{3, 2, 1, 5, 6, 4}
	generic(t, findKthLargest(nums, 2), 5)
}

func TestExample2(t *testing.T) {
	nums := []int{2, 1}
	generic(t, findKthLargest(nums, 2), 1)
}

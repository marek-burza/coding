package lc154

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("FindMin - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	nums := []int{4, 5, 6, 7, 0, 1, 2}
	generic(t, findMin(nums), 0)
}

func TestTrickier(t *testing.T) {
	nums := []int{1, 1, 0, 1, 1, 1, 1}
	generic(t, findMin(nums), 0)
}

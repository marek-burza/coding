package lc300

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("LengthOfLIS - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	nums := []int{10, 9, 2, 5, 3, 7, 101, 18}
	generic(t, lengthOfLIS(nums), 4)
}

func TestNothing(t *testing.T) {
	generic(t, lengthOfLIS([]int{}), 0)
}

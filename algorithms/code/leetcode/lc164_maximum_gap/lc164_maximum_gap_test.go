package lc164

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MaximumGap - Expected %v, got %v!", expected, result)
	}
}

func Test33210070(t *testing.T) {
	nums1 := []int{33, 2, 100, 70}
	generic(t, maximumGap(nums1), 37)
}

func TestNothing(t *testing.T) {
	generic(t, maximumGap([]int{}), 0)
}

package lc128

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("LongestConsecutive - Expected %v, got %v!", expected, result)
	}
}

func Test10042001032(t *testing.T) {
	nums1 := []int{100, 4, 200, 1, 3, 2}
	generic(t, longestConsecutive(nums1), 4)
}

func TestLonger(t *testing.T) {
	nums2 := []int{4, 2, 2, -4, 0, -2, 4, -3, -4, -4, -5, 1, 4, -9, 5, 0, 6, -8, -1, -3, 6, 5, -8, -1, -5, -1, 2, -9, 1}
	generic(t, longestConsecutive(nums2), 8)
}

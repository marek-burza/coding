package lc055

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("CanJump - Expected %v, got %v!", expected, result)
	}
}

func Test25002Integers(t *testing.T) {
	nums1 := make([]int, 25003)
	for i := range nums1 {
		nums1[i] = 25000 - i
	}
	nums1[25000] = 1
	nums1[25001] = 0
	nums1[25002] = 0
	generic(t, canJump(nums1), false)
}

func Test123(t *testing.T) {
	nums2 := []int{1, 2, 3}
	generic(t, canJump(nums2), true)
}

func TestNothing(t *testing.T) {
	generic(t, canJump([]int{}), false)
	generic(t, canJump([]int{0}), true)
}

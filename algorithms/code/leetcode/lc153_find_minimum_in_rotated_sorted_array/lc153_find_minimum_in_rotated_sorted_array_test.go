package lc153

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("FindMin - Expected %v, got %v!", expected, result)
	}
}

func Test0124567(t *testing.T) {
	nums := []int{0, 1, 2, 4, 5, 6, 7}
	generic(t, findMin(nums), 0)
}

func Test4567012(t *testing.T) {
	nums := []int{4, 5, 6, 7, 0, 1, 2}
	generic(t, findMin(nums), 0)
}

func Test12(t *testing.T) {
	nums := []int{1, 2}
	generic(t, findMin(nums), 1)
}

func Test21(t *testing.T) {
	nums := []int{2, 1}
	generic(t, findMin(nums), 1)
}

func Test1(t *testing.T) {
	nums := []int{1}
	generic(t, findMin(nums), 1)
}

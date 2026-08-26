package lc053

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MaxSubArray - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	generic(t, maxSubArray([]int{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6)
}

func TestExample2(t *testing.T) {
	generic(t, maxSubArray([]int{1}), 1)
}

func TestExample3(t *testing.T) {
	generic(t, maxSubArray([]int{5, 4, -1, 7, 8}), 23)
}

func TestMinus21(t *testing.T) {
	generic(t, maxSubArray([]int{-2, 1}), 1)
}

func TestMinus2Minus1(t *testing.T) {
	generic(t, maxSubArray([]int{-2, -1}), -1)
}

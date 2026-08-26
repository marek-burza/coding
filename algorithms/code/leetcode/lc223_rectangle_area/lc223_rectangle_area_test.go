package lc223

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("ComputeArea - Expected %v, got %v!", expected, result)
	}
}

func TestMinus3034And0Minus192(t *testing.T) {
	generic(t, computeArea(-3, 0, 3, 4, 0, -1, 9, 2), 45)
}

func TestMinus2Minus222AndMinus1416(t *testing.T) {
	generic(t, computeArea(-2, -2, 2, 2, -1, 4, 1, 6), 20)
}

func TestMinus5Minus5Minus40AndMinus3Minus333(t *testing.T) {
	generic(t, computeArea(-5, -5, -4, 0, -3, -3, 3, 3), 41)
}

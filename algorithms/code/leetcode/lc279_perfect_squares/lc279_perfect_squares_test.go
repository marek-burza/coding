package lc279

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("NumSquares - Expected %v, got %v!", expected, result)
	}
}

func Test12(t *testing.T) {
	generic(t, numSquares(12), 3)
}

func Test13(t *testing.T) {
	generic(t, numSquares(13), 2)
}

func Test9975(t *testing.T) {
	generic(t, numSquares(9975), 4)
}

func Test9732(t *testing.T) {
	generic(t, numSquares(9732), 3)
}

func Test5756(t *testing.T) {
	generic(t, numSquares(5756), 4)
}

func Test6255(t *testing.T) {
	generic(t, numSquares(6255), 4)
}

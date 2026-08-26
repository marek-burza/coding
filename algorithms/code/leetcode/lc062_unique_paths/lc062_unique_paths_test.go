package lc062

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("UniquePaths - Expected %v, got %v!", expected, result)
	}
}

func Test37(t *testing.T) {
	generic(t, uniquePaths(3, 7), 28)
}

func Test595(t *testing.T) {
	generic(t, uniquePaths(59, 5), 557845)
}

func Test110(t *testing.T) {
	generic(t, uniquePaths(1, 10), 1)
}

func TestNothing(t *testing.T) {
	generic(t, uniquePaths(1, 0), 0)
}

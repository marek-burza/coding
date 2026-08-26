package lc096

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("NumTrees - Expected %v, got %v!", expected, result)
	}
}

func Test2(t *testing.T) {
	generic(t, numTrees(2), 2)
}

func Test3(t *testing.T) {
	generic(t, numTrees(3), 5)
}

func Test19(t *testing.T) {
	generic(t, numTrees(19), 1767263190)
}

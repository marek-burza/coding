package lc372

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("SuperPow - Expected %v, got %v!", expected, result)
	}
}

func Test23(t *testing.T) {
	generic(t, superPow(2, []int{3}), 8)
}

func Test210(t *testing.T) {
	generic(t, superPow(2, []int{1, 0}), 1024)
}

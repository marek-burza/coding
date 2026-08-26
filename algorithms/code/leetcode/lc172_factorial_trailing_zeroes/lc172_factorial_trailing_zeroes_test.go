package lc172

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("TrailingZeroes - Expected %v, got %v!", expected, result)
	}
}

func Test5(t *testing.T) {
	generic(t, trailingZeroes(5), 1)
}

func Test1808548329(t *testing.T) {
	generic(t, trailingZeroes(1808548329), 452137076)
}

func Test2147483647(t *testing.T) {
	generic(t, trailingZeroes(2147483647), 536870902)
}

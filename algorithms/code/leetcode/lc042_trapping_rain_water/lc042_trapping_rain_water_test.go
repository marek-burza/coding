package lc042

import (
	"testing"
)

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("Trap - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	terrain := []int{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
	generic(t, trap(terrain), 6)
}

func TestNothing(t *testing.T) {
	generic(t, trap(nil), 0)
	generic(t, trap([]int{0, 1}), 0)
}

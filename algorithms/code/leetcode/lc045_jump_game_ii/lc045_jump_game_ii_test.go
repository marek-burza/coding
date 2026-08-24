package lc045

import (
	"testing"
)

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("Jump - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	nums := []int{2, 3, 1, 1, 4}
	generic(t, jump(nums), 2)
}

func TestNothing(t *testing.T) {
	nums := []int{0}
	generic(t, jump(nums), 0)
}

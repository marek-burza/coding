package lc162

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("FindPeakElement - Expected %v, got %v!", expected, result)
	}
}

func Test1231(t *testing.T) {
	generic(t, findPeakElement([]int{1, 2, 3, 1}), 2)
}

func Test1234(t *testing.T) {
	generic(t, findPeakElement([]int{1, 2, 3, 4}), 3)
}

func TestNothing(t *testing.T) {
	generic(t, findPeakElement([]int{}), -1)
}

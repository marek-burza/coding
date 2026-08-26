package lc122

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MaxProfit - Expected %v, got %v!", expected, result)
	}
}

func TestEmpty(t *testing.T) {
	generic(t, maxProfit([]int{}), 0)
}

func TestExample(t *testing.T) {
	generic(t, maxProfit([]int{1, 2, 1, 3, 2, 5, 0, 10, 9, 6, 3}), 16)
}

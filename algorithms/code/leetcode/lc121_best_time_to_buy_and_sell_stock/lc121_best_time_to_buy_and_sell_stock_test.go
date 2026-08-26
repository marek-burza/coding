package lc121

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MaxProfit - Expected %v, got %v!", expected, result)
	}
}

func TestEmpty(t *testing.T) {
	generic(t, maxProfit([]int{}), 0)
}

func Test1(t *testing.T) {
	generic(t, maxProfit([]int{1}), 0)
}

func TestExample1(t *testing.T) {
	generic(t, maxProfit([]int{7, 1, 5, 3, 6, 4}), 5)
}

func TestExample2(t *testing.T) {
	generic(t, maxProfit([]int{7, 6, 4, 3, 1}), 0)
}

package lc303

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("SumRange - Expected %v, got %v!", expected, result)
	}
}

func Test0And2(t *testing.T) {
	nums := []int{-2, 0, 3, -5, 2, -1}
	generic(t, NewNumArray(nums).SumRange(0, 2), 1)
}

func Test2And5(t *testing.T) {
	nums := []int{-2, 0, 3, -5, 2, -1}
	generic(t, NewNumArray(nums).SumRange(2, 5), -1)
}

func Test0And5(t *testing.T) {
	nums := []int{-2, 0, 3, -5, 2, -1}
	generic(t, NewNumArray(nums).SumRange(0, 5), -3)
}

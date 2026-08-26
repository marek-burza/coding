package lc137

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("SingleNumber - Expected %v, got %v!", expected, result)
	}
}

func Test1112(t *testing.T) {
	nums := []int{1, 1, 1, 2}
	generic(t, singleNumber(nums), 2)
}

func Test4344533(t *testing.T) {
	nums := []int{4, 3, 4, 4, 5, 3, 3}
	generic(t, singleNumber(nums), 5)
}

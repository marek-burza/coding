package lc136

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("SingleNumber - Expected %v, got %v!", expected, result)
	}
}

func Test1(t *testing.T) {
	generic(t, singleNumber([]int{1}), 1)
}

func Test121(t *testing.T) {
	generic(t, singleNumber([]int{1, 2, 1}), 2)
}

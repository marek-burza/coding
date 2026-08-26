package lc275

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("HIndex - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	citations := []int{0, 1, 3, 5, 6}
	generic(t, hIndex(citations), 3)
}

func TestNone(t *testing.T) {
	citations := []int{0, 0, 0, 0, 0}
	generic(t, hIndex(citations), 0)
}

func Test100(t *testing.T) {
	citations := []int{100}
	generic(t, hIndex(citations), 1)
}

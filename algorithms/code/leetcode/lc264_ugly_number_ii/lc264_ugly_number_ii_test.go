package lc264

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("NthUglyNumber - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	expected := []int{1, 2, 3, 4, 5, 6, 8, 9, 10, 12}
	for i, expectedI := range expected {
		generic(t, nthUglyNumber(i+1), expectedI)
	}
}

func TestBigger(t *testing.T) {
	generic(t, nthUglyNumber(1407), 536870912)
}

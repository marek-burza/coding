package lc204

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("CountPrimes - Expected %v, got %v!", expected, result)
	}
}

func Test11(t *testing.T) {
	generic(t, countPrimes(11), 4)
}

func Test1(t *testing.T) {
	generic(t, countPrimes(1), 0)
}

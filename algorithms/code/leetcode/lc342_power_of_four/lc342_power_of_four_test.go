package lc342

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsPowerOfFour - Expected %v, got %v!", expected, result)
	}
}

func Test16(t *testing.T) {
	generic(t, isPowerOfFour(16), true)
}

func Test5(t *testing.T) {
	generic(t, isPowerOfFour(5), false)
}

func TestNonPositive(t *testing.T) {
	generic(t, isPowerOfFour(0), false)
	generic(t, isPowerOfFour(-1), false)
}

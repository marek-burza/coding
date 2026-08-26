package lc326

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsPowerOfThree - Expected %v, got %v!", expected, result)
	}
}

func Test27(t *testing.T) {
	generic(t, isPowerOfThree(27), true)
}

func Test11(t *testing.T) {
	generic(t, isPowerOfThree(11), false)
}

func Test1(t *testing.T) {
	generic(t, isPowerOfThree(1), true)
}

func Test0(t *testing.T) {
	generic(t, isPowerOfThree(0), false)
}

func TestMinus3(t *testing.T) {
	generic(t, isPowerOfThree(-3), false)
}

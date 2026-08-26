package lc231

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsPowerOfTwo - Expected %v, got %v!", expected, result)
	}
}

func TestMinus10(t *testing.T) {
	generic(t, isPowerOfTwo(-10), false)
}

func Test0(t *testing.T) {
	generic(t, isPowerOfTwo(0), false)
}

func Test1(t *testing.T) {
	generic(t, isPowerOfTwo(1), true)
}

func Test2(t *testing.T) {
	generic(t, isPowerOfTwo(2), true)
}

func Test5(t *testing.T) {
	generic(t, isPowerOfTwo(5), false)
}

func Test1024(t *testing.T) {
	generic(t, isPowerOfTwo(1024), true)
}

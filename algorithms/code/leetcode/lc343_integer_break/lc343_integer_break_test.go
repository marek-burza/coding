package lc343

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("IntegerBreak - Expected %v, got %v!", expected, result)
	}
}

func Test2(t *testing.T) {
	generic(t, integerBreak(2), 1)
}

func Test3(t *testing.T) {
	generic(t, integerBreak(3), 2)
}

func Test4(t *testing.T) {
	generic(t, integerBreak(4), 4)
}

func Test5(t *testing.T) {
	generic(t, integerBreak(5), 6)
}

func Test6(t *testing.T) {
	generic(t, integerBreak(6), 9)
}

func Test7(t *testing.T) {
	generic(t, integerBreak(7), 12)
}

func Test8(t *testing.T) {
	generic(t, integerBreak(8), 18)
}

func Test9(t *testing.T) {
	generic(t, integerBreak(9), 27)
}

func Test10(t *testing.T) {
	generic(t, integerBreak(10), 36)
}

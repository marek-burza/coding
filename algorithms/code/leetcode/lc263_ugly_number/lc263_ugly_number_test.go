package lc263

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsUgly - Expected %v, got %v!", expected, result)
	}
}

func TestMinus(t *testing.T) {
	generic(t, isUgly(-1), false)
}

func Test0(t *testing.T) {
	generic(t, isUgly(0), false)
}

func Test1(t *testing.T) {
	generic(t, isUgly(1), true)
}

func Test2(t *testing.T) {
	generic(t, isUgly(2), true)
}

func Test3(t *testing.T) {
	generic(t, isUgly(3), true)
}

func Test7(t *testing.T) {
	generic(t, isUgly(7), false)
}

func Test11(t *testing.T) {
	generic(t, isUgly(11), false)
}

func Test14(t *testing.T) {
	generic(t, isUgly(14), false)
}

func Test16(t *testing.T) {
	generic(t, isUgly(16), true)
}

func Test27(t *testing.T) {
	generic(t, isUgly(27), true)
}

func Test937351770(t *testing.T) {
	generic(t, isUgly(937351770), false)
}

func Test905391974(t *testing.T) {
	generic(t, isUgly(905391974), false)
}

package lc357

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("CountNumbersWithUniqueDigits - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	generic(t, countNumbersWithUniqueDigits(2), 91)
}

func Test0(t *testing.T) {
	generic(t, countNumbersWithUniqueDigits(0), 1)
}

func Test1(t *testing.T) {
	generic(t, countNumbersWithUniqueDigits(1), 10)
}

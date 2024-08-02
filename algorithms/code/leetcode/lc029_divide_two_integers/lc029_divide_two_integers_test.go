package lc029

import (
    "math"
	"testing"
)

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("Divide - Expected %v, got %v!", expected, result)
	}
}

func TestMinus1010369383Minus2147483648(t *testing.T) {
    expected := -1010369383 / -2147483648
    result := Divide(-1010369383, -2147483648)
    generic(t, result, expected)
}

func TestMinus2147483648Minus1(t *testing.T) {
    expected := math.MaxInt32
    result := Divide(-2147483648, -1)
    generic(t, result, expected)
}

func TestDivisor0(t *testing.T) {
    expected := math.MaxInt32
    result := Divide(1, 0)
    generic(t, result, expected)
}

func TestDivisor1(t *testing.T) {
    expected := 0
    result := Divide(0, 1)
    generic(t, result, expected)
}

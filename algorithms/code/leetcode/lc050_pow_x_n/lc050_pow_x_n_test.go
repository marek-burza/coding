package lc050

import (
	"math"
	"testing"
)

func generic(t *testing.T, result float64, expected float64) {
	if expected != result {
		t.Errorf("MyPow - Expected %v, got %v!", expected, result)
	}
}

func TestSmaller(t *testing.T) {
	x := 34.00515
	n := -3
	expected := math.Pow(x, float64(n))
	generic(t, myPow(x, n), expected)
}

func TestBigger(t *testing.T) {
	x := 0.00001
	n := 2147483647
	expected := math.Pow(x, float64(n))
	generic(t, myPow(x, n), expected)
}

func Test0(t *testing.T) {
	generic(t, myPow(0, 0), 1.0)
}

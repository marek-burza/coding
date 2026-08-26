package lc367

import (
	"math"
	"testing"
)

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsPerfectSquare - Expected %v, got %v!", expected, result)
	}
}

func Test1(t *testing.T) {
	generic(t, isPerfectSquare(1), true)
}

func Test14(t *testing.T) {
	generic(t, isPerfectSquare(14), false)
}

func Test16(t *testing.T) {
	generic(t, isPerfectSquare(16), true)
}

func TestMaximum(t *testing.T) {
	generic(t, isPerfectSquare(math.MaxInt32), false)
}

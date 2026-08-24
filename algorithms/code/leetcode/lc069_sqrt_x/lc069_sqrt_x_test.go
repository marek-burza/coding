package lc069

import (
	"testing"
)

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MySqrt - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	generic(t, mySqrt(4), 2)
}

func TestExample2(t *testing.T) {
	generic(t, mySqrt(8), 2)
}

func Test64(t *testing.T) {
	generic(t, mySqrt(64), 8)
}

func Test2(t *testing.T) {
	generic(t, mySqrt(2), 1)
}

func Test1(t *testing.T) {
	generic(t, mySqrt(1), 1)
}

package lc008

import (
	"math"
	"testing"
)

func TestMinusMinus3241(t *testing.T) {
	if result := myAtoi("--3241"); result != 0 {
		t.Errorf("MyAtoi - Expected 0, but got %v!", result)
	}
}

func TestPlusPlus3241(t *testing.T) {
	if result := myAtoi("++3241"); result != 0 {
		t.Errorf("MyAtoi - Expected 0, but got %v!", result)
	}
}

func TestMinusPlus3241(t *testing.T) {
	if result := myAtoi("-+3241"); result != 0 {
		t.Errorf("MyAtoi - Expected 0, but got %v!", result)
	}
}

func TestMinus3241(t *testing.T) {
	if result := myAtoi("-3241"); result != -3241 {
		t.Errorf("MyAtoi - Expected -3241, but got %v!", result)
	}
}

func TestSpaceMinusPlus3241A(t *testing.T) {
	if result := myAtoi(" -3241a"); result != -3241 {
		t.Errorf("MyAtoi - Expected -3241, but got %v!", result)
	}
}

func Test9223372036854775809(t *testing.T) {
	if result := myAtoi("9223372036854775809"); result != math.MaxInt32 {
		t.Errorf("MyAtoi - Expected %v, but got %v!", math.MaxInt32, result)
	}
}

func TestMinus9223372036854775809(t *testing.T) {
	if result := myAtoi("-9223372036854775809"); result != math.MinInt32 {
		t.Errorf("MyAtoi - Expected %v, but got %v!", math.MinInt32, result)
	}
}

func TestNothing(t *testing.T) {
	if result := myAtoi("nothing"); result != 0 {
		t.Errorf("MyAtoi - Expected 0, but got %v!", result)
	}
}

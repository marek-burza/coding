package lc067

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("AddBinary - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	generic(t, addBinary("11", "1"), "100")
}

func TestExample2(t *testing.T) {
	generic(t, addBinary("1010", "1011"), "10101")
}

func TestExample1Reversed(t *testing.T) {
	generic(t, addBinary("1", "11"), "100")
}

func TestNoCarry(t *testing.T) {
	generic(t, addBinary("1", "0"), "1")
}

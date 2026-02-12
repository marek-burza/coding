package lc013

import (
	"testing"
)

func TestMcmliv(t *testing.T) {
	result := romanToInt("MCMLIV")
	expected := 1954
	if result != expected {
		t.Errorf("RomanToInt - Expected %d, but got %d!", expected, result)
	}
}

func TestNothing(t *testing.T) {
	result := romanToInt("")
	expected := 0
	if result != expected {
		t.Errorf("RomanToInt - Expected %d, but got %d!", expected, result)
	}
}

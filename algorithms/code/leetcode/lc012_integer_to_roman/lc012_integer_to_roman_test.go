package lc012

import (
	"testing"
)

func Test1234(t *testing.T) {
	result := intToRoman(1234)
	expected := "MCCXXXIV"
	if result != expected {
		t.Errorf("IntToRoman - Expected %s but got %s!", expected, result)
	}
}

func Test9(t *testing.T) {
	result := intToRoman(9)
	expected := "IX"
	if result != expected {
		t.Errorf("IntToRoman - Expected %s but got %s!", expected, result)
	}
}

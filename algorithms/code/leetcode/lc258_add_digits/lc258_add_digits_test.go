package lc258

import "testing"

func TestExample(t *testing.T) {
	expected := 2
	result := addDigits(38)
	if expected != result {
		t.Errorf("AddDigits - Expected %v, got %v!", expected, result)
	}
}

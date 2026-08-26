package lc371

import "testing"

func TestExample(t *testing.T) {
	expected := 3
	result := getSum(1, 2)
	if expected != result {
		t.Errorf("GetSum - Expected %v, got %v!", expected, result)
	}
}

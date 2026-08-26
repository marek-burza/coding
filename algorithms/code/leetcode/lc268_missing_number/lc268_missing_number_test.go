package lc268

import "testing"

func TestExample(t *testing.T) {
	expected := 2
	result := missingNumber([]int{0, 1, 3})
	if expected != result {
		t.Errorf("MissingNumber - Expected %v, got %v!", expected, result)
	}
}

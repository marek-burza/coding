package lc191

import "testing"

func Test11(t *testing.T) {
	expected := 3
	result := hammingWeight(11)
	if expected != result {
		t.Errorf("HammingWeight - Expected %v, got %v!", expected, result)
	}
}

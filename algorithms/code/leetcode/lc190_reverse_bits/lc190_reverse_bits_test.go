package lc190

import "testing"

func Test43261596(t *testing.T) {
	expected := 964176192
	result := reverseBits(43261596)
	if expected != result {
		t.Errorf("ReverseBits - Expected %v, got %v!", expected, result)
	}
}

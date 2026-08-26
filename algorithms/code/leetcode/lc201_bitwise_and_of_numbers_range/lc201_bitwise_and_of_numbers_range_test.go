package lc201

import "testing"

func Test5And7(t *testing.T) {
	expected := 4
	result := rangeBitwiseAnd(5, 7)
	if expected != result {
		t.Errorf("RangeBitwiseAnd - Expected %v, got %v!", expected, result)
	}
}

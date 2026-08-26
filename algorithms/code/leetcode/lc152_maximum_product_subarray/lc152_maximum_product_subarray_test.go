package lc152

import "testing"

func Test23Minus24(t *testing.T) {
	nums := []int{2, 3, -2, 4}
	expected := 6
	result := maxProduct(nums)
	if expected != result {
		t.Errorf("MaxProduct - Expected %v, got %v!", expected, result)
	}
}

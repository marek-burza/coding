package lc198

import "testing"

func Test664843310(t *testing.T) {
	nums := []int{6, 6, 4, 8, 4, 3, 3, 10}
	expected := 27
	result := rob(nums)
	if expected != result {
		t.Errorf("Rob - Expected %v, got %v!", expected, result)
	}
}

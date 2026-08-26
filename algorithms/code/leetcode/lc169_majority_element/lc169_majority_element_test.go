package lc169

import "testing"

func Test12315161(t *testing.T) {
	nums := []int{1, 2, 3, 1, 5, 1, 6, 1}
	expected := 1
	result := majorityElement(nums)
	if expected != result {
		t.Errorf("MajorityElement - Expected %v, got %v!", expected, result)
	}
}

package lc377

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("CombinationSum4 - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	nums := []int{1, 2, 3}
	generic(t, combinationSum4(nums, 4), 7)
}

func TestLongerExample(t *testing.T) {
	nums := []int{4, 2, 1}
	generic(t, combinationSum4(nums, 32), 39882198)
}

func TestWithGaps(t *testing.T) {
	nums := []int{3, 2}
	generic(t, combinationSum4(nums, 15), 28)
}

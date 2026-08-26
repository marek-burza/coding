package lc378

import "testing"

func TestExample(t *testing.T) {
	matrix := [][]int{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}
	expected := 13
	result := kthSmallest(matrix, 8)
	if expected != result {
		t.Errorf("KthSmallest - Expected %v, got %v!", expected, result)
	}
}

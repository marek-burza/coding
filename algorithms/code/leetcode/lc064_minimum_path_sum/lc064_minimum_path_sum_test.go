package lc064

import (
	"testing"
)

func TestExample(t *testing.T) {
	grid := [][]int{{1, 1, 2, 2}, {2, 1, 2, 2}, {2, 1, 1, 2}, {2, 2, 1, 1}}
	expected := 7
	result := minPathSum(grid)
	if expected != result {
		t.Errorf("MinPathSum - Expected %v, got %v!", expected, result)
	}
}

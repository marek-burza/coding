package diagonaldifference

import (
	"testing"
)

func TestExample(t *testing.T) {
	arr := [][]int32{
		{11, 2, 4},
		{4, 5, 6},
		{10, 8, -12},
	}
	if 15 != DiagonalDifference(arr) {
		t.Errorf("Staircase failed with example!")
	}
}

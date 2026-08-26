package lc120

import "testing"

func construct(compact [][]int) [][]int {
	var triangle [][]int
	for _, array := range compact {
		var line []int
		line = append(line, array...)
		triangle = append(triangle, line)
	}
	return triangle
}

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MinimumTotal - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	triangle := [][]int{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}}
	generic(t, minimumTotal(construct(triangle)), 11)
}

func TestNothing(t *testing.T) {
	generic(t, minimumTotal([][]int{}), 0)
	generic(t, minimumTotal([][]int{{}}), 0)
}

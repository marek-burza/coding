package lc240

import "testing"

var exampleMatrix = [][]int{
	{1, 4, 7, 11, 15},
	{2, 5, 8, 12, 19},
	{3, 6, 9, 16, 22},
	{10, 13, 14, 17, 24},
	{18, 21, 23, 26, 30},
}

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("SearchMatrix - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	generic(t, searchMatrix(exampleMatrix, 5), true)
}

func TestExample2(t *testing.T) {
	generic(t, searchMatrix(exampleMatrix, 20), false)
}

func TestOther1(t *testing.T) {
	matrix := [][]int{{1, 4}, {2, 5}}
	generic(t, searchMatrix(matrix, 2), true)
}

func TestOther2(t *testing.T) {
	matrix := [][]int{{-1, 3}}
	generic(t, searchMatrix(matrix, 1), false)
}

func TestOther3(t *testing.T) {
	matrix := [][]int{
		{1, 2, 3, 4, 5},
		{6, 7, 8, 9, 10},
		{11, 12, 13, 14, 15},
		{16, 17, 18, 19, 20},
		{21, 22, 23, 24, 25},
	}
	generic(t, searchMatrix(matrix, 5), true)
}

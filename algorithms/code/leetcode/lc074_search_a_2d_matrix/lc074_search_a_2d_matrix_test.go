package lc074

import (
	"testing"
)

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("SearchMatrix - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	matrix := [][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}
	generic(t, searchMatrix(matrix, 3), true)
}

func TestExample2(t *testing.T) {
	matrix := [][]int{{1}}
	generic(t, searchMatrix(matrix, 1), true)
}

func TestExample3(t *testing.T) {
	matrix := [][]int{{1}}
	generic(t, searchMatrix(matrix, 0), false)
}

func TestExample4(t *testing.T) {
	matrix := [][]int{{1, 1}}
	generic(t, searchMatrix(matrix, 0), false)
}

func TestExample5(t *testing.T) {
	matrix := [][]int{{1, 1}}
	generic(t, searchMatrix(matrix, 2), false)
}

func TestExample6(t *testing.T) {
	matrix := [][]int{{1}, {3}}
	generic(t, searchMatrix(matrix, 1), true)
}

func TestOther(t *testing.T) {
	matrix := [][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}
	generic(t, searchMatrix(matrix, 13), false)
}

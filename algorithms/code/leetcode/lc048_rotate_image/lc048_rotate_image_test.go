package lc048

import (
	"reflect"
	"testing"
)

func testMatrices(t *testing.T, expected [][]int, result [][]int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Rotate - Expected %v, got %v!", expected, result)
	}
}

func TestEven(t *testing.T) {
	matrix := [][]int{{0, 1}, {2, 3}}
	expected := [][]int{{2, 0}, {3, 1}}
	rotate(matrix)
	testMatrices(t, expected, matrix)
}

func TestOdd(t *testing.T) {
	matrix := [][]int{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}}
	expected := [][]int{{6, 3, 0}, {7, 4, 1}, {8, 5, 2}}
	rotate(matrix)
	testMatrices(t, expected, matrix)
}

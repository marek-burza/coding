package lc054

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected []int, matrix [][]int) {
	result := spiralOrder(matrix)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("SpiralOrder - Expected %v, got %v!", expected, result)
	}
}

func Test258And40Minus1(t *testing.T) {
	matrix := [][]int{{2, 5, 8}, {4, 0, -1}}
	expected := []int{2, 5, 8, -1, 0, 4}
	generic(t, expected, matrix)
}

func Test25And84And0Minus1(t *testing.T) {
	matrix := [][]int{{2, 5}, {8, 4}, {0, -1}}
	expected := []int{2, 5, 4, -1, 0, 8}
	generic(t, expected, matrix)
}

func TestNothing(t *testing.T) {
	if len(spiralOrder([][]int{})) != 0 {
		t.Errorf("SpiralOrder - Expected nothing, got %v!", spiralOrder([][]int{}))
	}
}

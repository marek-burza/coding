package lc073

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected [][]int, matrix [][]int) {
	if !reflect.DeepEqual(expected, matrix) {
		t.Errorf("SetZeroes - Expected %v, got %v!", expected, matrix)
	}
}

func TestSmallerExample1(t *testing.T) {
	matrix := [][]int{{1, 0}}
	expected := [][]int{{0, 0}}
	setZeroes(matrix)
	generic(t, expected, matrix)
}

func TestSmallerExample2(t *testing.T) {
	matrix := [][]int{{0, 1}}
	expected := [][]int{{0, 0}}
	setZeroes(matrix)
	generic(t, expected, matrix)
}

func TestBiggerExample(t *testing.T) {
	matrix := [][]int{
		{0, 0, 0, 5},
		{4, 3, 1, 4},
		{0, 1, 1, 4},
		{1, 2, 1, 3},
		{0, 0, 1, 1},
	}
	expected := [][]int{
		{0, 0, 0, 0},
		{0, 0, 0, 4},
		{0, 0, 0, 0},
		{0, 0, 0, 3},
		{0, 0, 0, 0},
	}
	setZeroes(matrix)
	generic(t, expected, matrix)
}

func TestOther(t *testing.T) {
	matrix := [][]int{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}
	expected := [][]int{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}
	setZeroes(matrix)
	generic(t, expected, matrix)
}

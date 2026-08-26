package lc289

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected [][]int, board [][]int) {
	if !reflect.DeepEqual(expected, board) {
		t.Errorf("GameOfLife - Expected %v, got %v!", expected, board)
	}
}

func TestEmpty(t *testing.T) {
	board := [][]int{{}}
	expected := [][]int{{}}
	gameOfLife(board)
	generic(t, expected, board)
}

func TestExample1(t *testing.T) {
	board := [][]int{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}}
	expected := [][]int{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}}
	gameOfLife(board)
	generic(t, expected, board)
}

func TestExample2(t *testing.T) {
	board := [][]int{{1, 1}, {1, 0}}
	expected := [][]int{{1, 1}, {1, 1}}
	gameOfLife(board)
	generic(t, expected, board)
}

func TestOther(t *testing.T) {
	board := [][]int{
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 1, 1, 1, 0},
		{0, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
	}
	expected := [][]int{
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 0, 0},
		{0, 1, 0, 0, 1, 0},
		{0, 1, 0, 0, 1, 0},
		{0, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
	}
	gameOfLife(board)
	generic(t, expected, board)
}

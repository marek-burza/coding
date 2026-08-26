package lc200

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("NumIslands - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	grid := [][]string{{"1"}}
	generic(t, numIslands(grid), 1)
}

func TestOther(t *testing.T) {
	grid := [][]string{
		{"1", "1", "0", "0", "0"},
		{"1", "1", "0", "0", "0"},
		{"0", "0", "1", "0", "0"},
		{"0", "0", "0", "1", "1"},
	}
	generic(t, numIslands(grid), 3)
}

func TestNothing(t *testing.T) {
	generic(t, numIslands([][]string{}), 0)
	generic(t, numIslands([][]string{{}}), 0)
}

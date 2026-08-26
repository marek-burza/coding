package lc218

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, buildings [][]int, expected [][]int) {
	skyline := getSkyline(buildings)
	if !reflect.DeepEqual(expected, skyline) {
		t.Errorf("GetSkyline - Expected %v, got %v!", expected, skyline)
	}
}

func TestExample1(t *testing.T) {
	buildings := [][]int{
		{2, 9, 10},
		{3, 7, 15},
		{5, 12, 12},
		{15, 20, 10},
		{19, 24, 8},
	}
	expected := [][]int{
		{2, 10},
		{3, 15},
		{7, 12},
		{12, 0},
		{15, 10},
		{20, 8},
		{24, 0},
	}
	generic(t, buildings, expected)
}

func TestExample2(t *testing.T) {
	buildings := [][]int{{0, 2, 3}, {2, 5, 3}}
	expected := [][]int{{0, 3}, {5, 0}}
	generic(t, buildings, expected)
}

func TestCoverageGaps(t *testing.T) {
	buildings := [][]int{{0, 2, 3}, {2, 5, 3}, {0, 0, 10}}
	expected := [][]int{{0, 3}, {5, 0}}
	generic(t, buildings, expected)
	if len(getSkyline([][]int{})) != 0 {
		t.Errorf("GetSkyline - Expected nothing!")
	}
}

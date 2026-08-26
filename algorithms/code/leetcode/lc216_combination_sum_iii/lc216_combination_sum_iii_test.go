package lc216

import (
	"reflect"
	"slices"
	"testing"
)

func generic(t *testing.T, expected [][]int, result [][]int) {
	for _, entry := range result {
		slices.Sort(entry)
	}
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("CombinationSum3 - Expected %v, got %v!", expected, result)
	}
}

func Test37(t *testing.T) {
	expected := [][]int{{1, 2, 4}}
	generic(t, expected, combinationSum3(3, 7))
}

func Test39(t *testing.T) {
	expected := [][]int{{1, 2, 6}, {1, 3, 5}, {2, 3, 4}}
	generic(t, expected, combinationSum3(3, 9))
}

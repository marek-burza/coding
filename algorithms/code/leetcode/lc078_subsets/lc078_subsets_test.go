package lc078

import (
	"reflect"
	"slices"
	"testing"
)

func generic(t *testing.T, expected [][]int, result [][]int) {
	for _, listed := range result {
		slices.Sort(listed)
	}
	for _, listed := range expected {
		slices.Sort(listed)
	}
	slices.SortFunc(result, slices.Compare)
	slices.SortFunc(expected, slices.Compare)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Subsets - Expected %v, got %v!", expected, result)
	}
}

func Test123(t *testing.T) {
	listed := subsets([]int{1, 2, 3})
	expected := [][]int{{}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}
	generic(t, expected, listed)
}

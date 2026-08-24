package lc090

import (
	"reflect"
	"slices"
	"testing"
)

func orderlyComparator(l1 []int, l2 []int) int {
	difference := len(l1) - len(l2)
	if difference != 0 {
		return difference
	}
	return slices.Compare(l1, l2)
}

func generic(t *testing.T, expected [][]int, result [][]int) {
	slices.SortFunc(result, orderlyComparator)
	for _, listed := range result {
		slices.Sort(listed)
	}
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("SubsetsWithDup - Expected %v, got %v!", expected, result)
	}
}

func Test122(t *testing.T) {
	listed := subsetsWithDup([]int{1, 2, 2})
	expected := [][]int{{}, {1}, {2}, {1, 2}, {2, 2}, {1, 2, 2}}
	generic(t, expected, listed)
}

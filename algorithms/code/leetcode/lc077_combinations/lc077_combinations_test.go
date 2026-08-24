package lc077

import (
	"reflect"
	"slices"
	"testing"
)

func integerListComparator(l1 []int, l2 []int) int {
	if len(l1) != len(l2) {
		return len(l1) - len(l2)
	}
	return slices.Compare(l1, l2)
}

func TestExample(t *testing.T) {
	expected := [][]int{{1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}}
	result := combine(4, 2)
	slices.SortFunc(result, integerListComparator)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Combine - Expected %v, got %v!", expected, result)
	}
}

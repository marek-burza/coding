package lc046

import (
	"reflect"
	"slices"
	"sort"
	"testing"
)

func integerListComparator(l1 []int, l2 []int) int {
	if len(l1) != len(l2) {
		return len(l1) - len(l2)
	}
	return slices.Compare(l1, l2)
}

func TestExample(t *testing.T) {
	nums := []int{1, 2, 3}
	expected := [][]int{
		{1, 2, 3},
		{1, 3, 2},
		{2, 1, 3},
		{2, 3, 1},
		{3, 1, 2},
		{3, 2, 1},
	}
	result := permute(nums)
	sort.Slice(result, func(i, j int) bool {
		return integerListComparator(result[i], result[j]) < 0
	})
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Permute - Expected %v, got %v!", expected, result)
	}
}

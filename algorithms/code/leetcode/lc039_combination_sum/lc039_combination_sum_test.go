package lc039

import (
	"reflect"
	"slices"
	"sort"
	"testing"
)

func deepComparator(list1 []int, list2 []int) int {
	if len(list1) != len(list2) {
		return len(list1) - len(list2)
	}
	return slices.Compare(list1, list2)
}

func TestExample(t *testing.T) {
	candidates := []int{2, 3, 6, 7}
	expected := [][]int{{7}, {2, 2, 3}}
	combos := combinationSum(candidates, 7)
	for _, listed := range combos {
		sort.Ints(listed)
	}
	sort.Slice(combos, func(i, j int) bool {
		return deepComparator(combos[i], combos[j]) < 0
	})
	if !reflect.DeepEqual(expected, combos) {
		t.Errorf("CombinationSum - Expected %v, got %v!", expected, combos)
	}
}

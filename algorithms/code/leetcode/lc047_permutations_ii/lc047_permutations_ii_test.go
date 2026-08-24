package lc047

import (
	"reflect"
	"slices"
	"testing"
)

func TestExample(t *testing.T) {
	nums := []int{1, 1, 2}
	result := permuteUnique(nums)
	slices.SortFunc(result, slices.Compare)
	expected := [][]int{{1, 1, 2}, {1, 2, 1}, {2, 1, 1}}
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("PermuteUnique - Expected %v, got %v!", expected, result)
	}
}

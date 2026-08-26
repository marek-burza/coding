package lc260

import (
	"reflect"
	"slices"
	"testing"
)

func TestSingleNumber(t *testing.T) {
	result := singleNumber([]int{1, 2, 1, 3, 2, 5})
	slices.Sort(result)
	expected := []int{3, 5}
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("SingleNumber - Expected %v, got %v!", expected, result)
	}
}

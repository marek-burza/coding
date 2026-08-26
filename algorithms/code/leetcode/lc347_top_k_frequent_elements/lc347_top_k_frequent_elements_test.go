package lc347

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	nums := []int{1, 1, 1, 2, 2, 3}
	expected := []int{1, 2}
	result := topKFrequent(nums, 2)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("TopKFrequent - Expected %v, got %v!", expected, result)
	}
}

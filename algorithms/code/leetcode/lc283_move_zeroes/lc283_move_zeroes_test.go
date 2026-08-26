package lc283

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	nums := []int{0, 1, 0, 3, 12}
	moveZeroes(nums)
	expected := []int{1, 3, 12, 0, 0}
	if !reflect.DeepEqual(expected, nums) {
		t.Errorf("MoveZeroes - Expected %v, got %v!", expected, nums)
	}
}

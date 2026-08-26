package lc167

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, result []int, expected []int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("TwoSum - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	generic(t, twoSum([]int{2, 7, 11, 15}, 9), []int{1, 2})
}

func TestOtherExample(t *testing.T) {
	generic(t, twoSum([]int{1, 5, 6, 9}, 9), []int{0, 0})
}

func TestNothing(t *testing.T) {
	generic(t, twoSum([]int{}, 0), []int{0, 0})
	generic(t, twoSum([]int{0}, 0), []int{0, 0})
}

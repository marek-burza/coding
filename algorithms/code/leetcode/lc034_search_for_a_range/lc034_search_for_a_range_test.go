package lc034

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, result []int, expected []int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("SearchRange - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	nums := []int{5, 7, 7, 8, 8, 10}
	expected := []int{3, 4}
	generic(t, searchRange(nums, 8), expected)
}

func TestOther(t *testing.T) {
	nums := []int{5, 7, 7, 8, 8, 10}
	expected := []int{-1, -1}
	generic(t, searchRange(nums, 6), expected)
}

func TestAnother(t *testing.T) {
	nums := []int{2, 2}
	expected := []int{-1, -1}
	generic(t, searchRange(nums, 3), expected)
}

func TestNothing(t *testing.T) {
	expected := []int{-1, -1}
	generic(t, searchRange([]int{}, 3), expected)
}

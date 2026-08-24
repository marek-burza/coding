package lc075

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected []int, nums []int) {
	if !reflect.DeepEqual(expected, nums) {
		t.Errorf("SortColors - Expected %v, got %v!", expected, nums)
	}
}

func Test2(t *testing.T) {
	nums := []int{2}
	expected := []int{2}
	sortColors(nums)
	generic(t, expected, nums)
}

func Test10(t *testing.T) {
	nums := []int{1, 0}
	expected := []int{0, 1}
	sortColors(nums)
	generic(t, expected, nums)
}

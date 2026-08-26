package lc189

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected []int, nums []int) {
	if !reflect.DeepEqual(expected, nums) {
		t.Errorf("Rotate - Expected %v, got %v!", expected, nums)
	}
}

func Test1234567And3(t *testing.T) {
	nums := []int{1, 2, 3, 4, 5, 6, 7}
	rotate(nums, 3)
	expected := []int{5, 6, 7, 1, 2, 3, 4}
	generic(t, expected, nums)
}

func Test123456And2(t *testing.T) {
	nums := []int{1, 2, 3, 4, 5, 6}
	rotate(nums, 2)
	expected := []int{5, 6, 1, 2, 3, 4}
	generic(t, expected, nums)
}

func Test1And2(t *testing.T) {
	nums := []int{1}
	rotate(nums, 1)
	expected := []int{1}
	generic(t, expected, nums)
}

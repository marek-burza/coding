package lc349

import (
	"reflect"
	"slices"
	"testing"
)

func generic(t *testing.T, expected []int, result []int) {
	slices.Sort(result)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Intersection - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	nums1 := []int{1, 2, 2, 1}
	nums2 := []int{2, 2}
	generic(t, []int{2}, intersection(nums1, nums2))
}

func TestExampleFlipped(t *testing.T) {
	nums1 := []int{2, 2}
	nums2 := []int{1, 2, 2, 1}
	generic(t, []int{2}, intersection(nums1, nums2))
}

func Test12And11(t *testing.T) {
	generic(t, []int{1}, intersection([]int{1, 2}, []int{1, 1}))
}

func Test495And94985(t *testing.T) {
	generic(t, []int{4, 5, 9}, intersection([]int{4, 9, 5}, []int{9, 4, 9, 8, 5}))
}

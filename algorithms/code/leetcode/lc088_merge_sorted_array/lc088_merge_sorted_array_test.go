package lc088

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected []int, nums1 []int) {
	if !reflect.DeepEqual(expected, nums1) {
		t.Errorf("Merge - Expected %v, got %v!", expected, nums1)
	}
}

func TestExample1(t *testing.T) {
	nums1 := []int{1, 2, 3, 0, 0, 0}
	nums2 := []int{2, 5, 6}
	expected := []int{1, 2, 2, 3, 5, 6}
	merge(nums1, 3, nums2, 3)
	generic(t, expected, nums1)
}

func TestExample2(t *testing.T) {
	nums1 := []int{1}
	nums2 := []int{}
	expected := []int{1}
	merge(nums1, 1, nums2, 0)
	generic(t, expected, nums1)
}

func TestExample3(t *testing.T) {
	nums1 := []int{0}
	nums2 := []int{1}
	expected := []int{1}
	merge(nums1, 0, nums2, 1)
	generic(t, expected, nums1)
}

func Test13711000And4And4620And3(t *testing.T) {
	nums1 := []int{1, 3, 7, 11, 0, 0, 0}
	nums2 := []int{4, 6, 20}
	expected := []int{1, 3, 4, 6, 7, 11, 20}
	merge(nums1, 4, nums2, 3)
	generic(t, expected, nums1)
}

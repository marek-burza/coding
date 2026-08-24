package lc080

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, nums []int, expected []int, count int) {
	result := removeDuplicates(nums)
	if count != result {
		t.Errorf("RemoveDuplicates - Expected %v, got %v!", count, result)
	}
	if !reflect.DeepEqual(expected, nums[:len(expected)]) {
		t.Errorf("RemoveDuplicates - Expected %v, got %v!", expected, nums[:len(expected)])
	}
}

func TestExample(t *testing.T) {
	nums := []int{1, 1, 1, 2, 2, 3}
	expected := []int{1, 1, 2, 2, 3}
	generic(t, nums, expected, 5)
}

func Test111133(t *testing.T) {
	nums := []int{1, 1, 1, 1, 3, 3}
	expected := []int{1, 1, 3, 3}
	generic(t, nums, expected, 4)
}

func Test11(t *testing.T) {
	nums := []int{1, 1}
	expected := []int{1, 1}
	generic(t, nums, expected, 2)
}

func Test122(t *testing.T) {
	nums := []int{1, 2, 2}
	expected := []int{1, 2, 2}
	generic(t, nums, expected, 3)
}

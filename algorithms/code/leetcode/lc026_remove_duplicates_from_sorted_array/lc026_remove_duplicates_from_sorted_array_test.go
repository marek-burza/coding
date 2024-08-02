package lc026

import (
    "reflect"
	"testing"
)

func generic(t *testing.T, result int, expected int, nums []int, expectedNums []int) {
	if expected != result {
		t.Errorf("RemoveElement - Expected %v, got %v!", expected, result)
	}
    if nums != nil && !reflect.DeepEqual(expectedNums, nums[:result]) {
        t.Errorf("RemoveElement - Expected %v, got %v", expectedNums, nums[:result])
    }
}

func Test1223447(t *testing.T) {
    nums := []int{1, 2, 2, 3, 4, 4, 7};
    expected := 5
    result := RemoveDuplicates(nums)
    generic(t, result, expected, nums, []int{1, 2, 3, 4, 7})
}

func TestNothing(t *testing.T) {
    expected := 0
    result := RemoveDuplicates([]int{})
    generic(t, result, expected, []int{}, []int{})
}

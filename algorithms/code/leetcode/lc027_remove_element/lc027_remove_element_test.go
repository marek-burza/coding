package lc027

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

func Test042124234And42(t *testing.T) {
	nums := []int{0, 42, 1, 2, 42, 3, 4}
	expected := 5
	result := RemoveElement(nums, 42)
	generic(t, result, expected, nums, []int{0, 1, 2, 3, 4})
}

func TestNothing(t *testing.T) {
	expected := 0
	result := RemoveElement(nil, 42)
	generic(t, result, expected, nil, nil)
}

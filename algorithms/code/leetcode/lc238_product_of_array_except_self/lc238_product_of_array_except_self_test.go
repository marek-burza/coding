package lc238

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected []int, actual []int) {
	if !reflect.DeepEqual(expected, actual) {
		t.Errorf("ProductExceptSelf - Expected %v, got %v!", expected, actual)
	}
}

func Test1234(t *testing.T) {
	nums := []int{1, 2, 3, 4}
	expected := []int{24, 12, 8, 6}
	generic(t, expected, productExceptSelf(nums))
}

func Test90Minus2(t *testing.T) {
	nums := []int{9, 0, -2}
	expected := []int{0, -18, 0}
	generic(t, expected, productExceptSelf(nums))
}

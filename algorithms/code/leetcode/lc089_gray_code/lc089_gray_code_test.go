package lc089

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected []int, result []int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("GrayCode - Expected %v, got %v!", expected, result)
	}
}

func Test4(t *testing.T) {
	expected := []int{0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8}
	generic(t, expected, grayCode(4))
}

func Test0(t *testing.T) {
	expected := []int{0}
	generic(t, expected, grayCode(0))
}

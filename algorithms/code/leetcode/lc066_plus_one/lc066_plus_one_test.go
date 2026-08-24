package lc066

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, result []int, expected []int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("PlusOne - Expected %v, got %v!", expected, result)
	}
}

func Test19(t *testing.T) {
	expected := []int{2, 0}
	generic(t, plusOne([]int{1, 9}), expected)
}

func Test99(t *testing.T) {
	expected := []int{1, 0, 0}
	generic(t, plusOne([]int{9, 9}), expected)
}

package lc338

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, result []int, expected []int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("CountBits - Expected %v, got %v!", expected, result)
	}
}

func Test2(t *testing.T) {
	generic(t, countBits(2), []int{0, 1, 1})
}

func Test5(t *testing.T) {
	generic(t, countBits(5), []int{0, 1, 1, 2, 1, 2})
}

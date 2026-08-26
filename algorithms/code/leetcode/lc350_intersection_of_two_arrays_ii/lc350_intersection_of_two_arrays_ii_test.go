package lc350

import (
	"reflect"
	"slices"
	"testing"
)

func generic(t *testing.T, expected []int, result []int) {
	slices.Sort(result)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Intersect - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	generic(t, []int{2, 2}, intersect([]int{1, 2, 2, 1}, []int{2, 2}))
}

func TestExampleFlipped(t *testing.T) {
	generic(t, []int{2, 2}, intersect([]int{2, 2}, []int{1, 2, 2, 1}))
}

func Test1And1(t *testing.T) {
	generic(t, []int{1}, intersect([]int{1}, []int{1}))
}

func Test12And11(t *testing.T) {
	generic(t, []int{1}, intersect([]int{1, 2}, []int{1, 1}))
}

func Test495And94985(t *testing.T) {
	generic(t, []int{4, 5, 9}, intersect([]int{4, 9, 5}, []int{9, 4, 9, 8, 5}))
}

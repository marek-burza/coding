package lc241

import (
	"reflect"
	"slices"
	"testing"
)

func generic(t *testing.T, expected []int, result []int) {
	slices.Sort(result)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("DiffWaysToCompute - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	expected := []int{0, 2}
	generic(t, expected, diffWaysToCompute("2-1-1"))
}

func TestExample2(t *testing.T) {
	expected := []int{-34, -14, -10, -10, 10}
	generic(t, expected, diffWaysToCompute("2*3-4*5"))
}

func TestOther(t *testing.T) {
	expected := []int{7}
	generic(t, expected, diffWaysToCompute("3+4"))
}

package equi

import (
	"testing"
)

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("FindEquilibriumIndex - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	expected := 1
	result := FindEquilibriumIndex([]int{-1, 3, -4, 5, 1, -6, 2, 1})
	generic(t, result, expected)
}

func TestEmpty(t *testing.T) {
	expected := -1
	result := FindEquilibriumIndex([]int{})
	generic(t, result, expected)
}

func TestInvalid(t *testing.T) {
	expected := -1
	result := FindEquilibriumIndex([]int{1, 2, 3, 4})
	generic(t, result, expected)
}

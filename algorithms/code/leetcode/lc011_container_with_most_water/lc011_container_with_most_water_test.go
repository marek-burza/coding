package lc011

import (
	"testing"
)

func TestMaxArea_1_2_1(t *testing.T) {
	test := []int{1, 2, 1}
	expected := 2
	if result := maxArea(test); result != expected {
		t.Errorf("MaxArea - Expected %d, but got %d!", expected, result)
	}
}

func TestMaxArea_1_3_5_2(t *testing.T) {
	test := []int{1, 3, 5, 2}
	expected := 4
	if result := maxArea(test); result != expected {
		t.Errorf("MaxArea - Expected %d, but got %d!", expected, result)
	}
}

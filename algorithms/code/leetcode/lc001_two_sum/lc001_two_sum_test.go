package lc001

import (
	"reflect"
	"testing"
)

func TestNull(t *testing.T) {
	result := twoSum(nil, 0)
	if result != nil {
		t.Errorf("TwoSum - Expected nil, but got %v!", result)
	}
}

func TestEmpty(t *testing.T) {
	result := twoSum([]int{}, 0)
	if result != nil {
		t.Errorf("TwoSum - Expected nil, but got %v!", result)
	}
}

func TestExample1(t *testing.T) {
	expected := []int{0, 1}
	result := twoSum([]int{2, 7, 11, 15}, 9)
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("TwoSum - Expected %v, but got %v!", expected, result)
	}
}

func TestExample2(t *testing.T) {
	expected := []int{1, 2}
	result := twoSum([]int{3, 2, 4}, 6)
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("TwoSum - Expected %v, but got %v!", expected, result)
	}
}

func TestExample3(t *testing.T) {
	expected := []int{0, 1}
	result := twoSum([]int{3, 3}, 6)
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("TwoSum - Expected %v, but got %v!", expected, result)
	}
}

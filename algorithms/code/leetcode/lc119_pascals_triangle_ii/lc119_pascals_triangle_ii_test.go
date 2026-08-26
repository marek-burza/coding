package lc119

import (
	"reflect"
	"testing"
)

func Test3(t *testing.T) {
	expected := []int{1, 3, 3, 1}
	result := getRow(3)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("GetRow - Expected %v, got %v!", expected, result)
	}
}

func TestNothing(t *testing.T) {
	if len(getRow(-2)) != 0 {
		t.Errorf("GetRow - Expected nothing, got %v!", getRow(-2))
	}
}

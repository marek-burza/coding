package lc059

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	expected := [][]int{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}
	result := generateMatrix(3)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("GenerateMatrix - Expected %v, got %v!", expected, result)
	}
}

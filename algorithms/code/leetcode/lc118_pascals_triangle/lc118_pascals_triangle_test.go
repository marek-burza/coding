package lc118

import (
	"reflect"
	"testing"
)

func Test5(t *testing.T) {
	expected := [][]int{{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}}
	result := generate(5)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Generate - Expected %v, got %v!", expected, result)
	}
}

func TestNothing(t *testing.T) {
	if len(generate(-1)) != 0 {
		t.Errorf("Generate - Expected nothing, got %v!", generate(-1))
	}
}

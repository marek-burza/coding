package staircase

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	expected := []string{
		"     #",
		"    ##",
		"   ###",
		"  ####",
		" #####",
		"######",
	}
	if !reflect.DeepEqual(expected, Staircase(6)) {
		t.Errorf("Staircase failed with example!")
	}
}

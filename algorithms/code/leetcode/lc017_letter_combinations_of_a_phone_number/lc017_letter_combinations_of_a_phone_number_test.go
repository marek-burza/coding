package lc017

import (
	"reflect"
	"sort"
	"testing"
)

func TestNothing(t *testing.T) {
	result := letterCombinations("")
	if len(result) != 0 {
		t.Errorf("LetterCombinations - Expected 0 results, got %v!", len(result))
	}
}

func TestEmpty(t *testing.T) {
	result := letterCombinations("")
	if len(result) != 0 {
		t.Errorf("LetterCombinations - Expected 0 results, got %v!", len(result))
	}
}

func TestExample(t *testing.T) {
	expected := []string{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"}
	result := letterCombinations("23")
	sort.Strings(expected)
	sort.Strings(result)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("LetterCombinations - Expected %v, got %v!", expected, result)
	}
}

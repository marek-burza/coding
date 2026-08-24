package lc049

import (
	"reflect"
	"slices"
	"testing"
)

func orderlyComparator(l1 []string, l2 []string) int {
	difference := len(l1) - len(l2)
	if difference != 0 {
		return difference
	}
	return slices.Compare(l1, l2)
}

func generic(t *testing.T, expected [][]string, result [][]string) {
	slices.SortFunc(result, orderlyComparator)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("GroupAnagrams - Expected %v, got %v!", expected, result)
	}
}

func TestAbcCabBadDabZzzDot(t *testing.T) {
	strs := []string{"abc", "cab", "bad", "dab", "zzz", "dot"}
	expected := [][]string{{"dot"}, {"zzz"}, {"abc", "cab"}, {"bad", "dab"}}
	result := groupAnagrams(strs)
	generic(t, expected, result)
}

func TestTeaAndAteEatDen(t *testing.T) {
	strs := []string{"tea", "and", "ate", "eat", "den"}
	expected := [][]string{{"and"}, {"den"}, {"ate", "eat", "tea"}}
	result := groupAnagrams(strs)
	generic(t, expected, result)
}

package lc083

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, result []int, expected []int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("DeleteDuplicates - Expected %v, got %v!", expected, result)
	}
}

func Test112(t *testing.T) {
	linked := listedToLinked([]int{1, 1, 2})
	result := deleteDuplicates(linked)
	generic(t, linkedToListed(result), []int{1, 2})
}

func Test11233(t *testing.T) {
	linked := listedToLinked([]int{1, 1, 2, 3, 3})
	result := deleteDuplicates(linked)
	generic(t, linkedToListed(result), []int{1, 2, 3})
}

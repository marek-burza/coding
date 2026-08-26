package lc217

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("ContainsDuplicate - Expected %v, got %v!", expected, result)
	}
}

func Test057(t *testing.T) {
	generic(t, containsDuplicate([]int{0, 5, 7}), false)
}

func Test057510(t *testing.T) {
	generic(t, containsDuplicate([]int{0, 5, 7, 5, 10}), true)
}

package lc219

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("ContainsNearbyDuplicate - Expected %v, got %v!", expected, result)
	}
}

func Test057And2(t *testing.T) {
	generic(t, containsNearbyDuplicate([]int{0, 5, 7}, 2), false)
}

func Test0575And2(t *testing.T) {
	generic(t, containsNearbyDuplicate([]int{0, 5, 7, 5}, 2), true)
}

func Test057105And2(t *testing.T) {
	generic(t, containsNearbyDuplicate([]int{0, 5, 7, 10, 5}, 2), false)
}

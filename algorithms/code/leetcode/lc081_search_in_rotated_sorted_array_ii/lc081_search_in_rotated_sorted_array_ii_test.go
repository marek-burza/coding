package lc081

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("Search - Expected %v, got %v!", expected, result)
	}
}

func TestSimpleExample(t *testing.T) {
	nums := []int{4, 5, 6, 6, 7, 0, 1, 2}
	generic(t, search(nums, 1), true)
}

func TestTrickyExample(t *testing.T) {
	nums := []int{1, 1, 3, 1, 1, 1, 1, 1}
	generic(t, search(nums, 2), false)
}

func Test1And1(t *testing.T) {
	nums := []int{1}
	generic(t, search(nums, 1), true)
}

func Test1And0(t *testing.T) {
	nums := []int{1}
	generic(t, search(nums, 0), false)
}

func Test2560012And3(t *testing.T) {
	nums := []int{2, 5, 6, 0, 0, 1, 2}
	generic(t, search(nums, 3), false)
}

func Test2560012And0(t *testing.T) {
	nums := []int{2, 5, 6, 0, 0, 1, 2}
	generic(t, search(nums, 0), true)
}

func Test2223222And3(t *testing.T) {
	nums := []int{2, 2, 2, 3, 2, 2, 2}
	generic(t, search(nums, 3), true)
}

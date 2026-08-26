package lc033

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("Search - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	nums := []int{4, 5, 6, 7, 0, 1, 2}
	generic(t, search(nums, 1), 5)
}

func TestSingle(t *testing.T) {
	nums := []int{1}
	generic(t, search(nums, 1), 0)
}

func TestOther(t *testing.T) {
	nums := []int{4, 5, 6, 7, 0, 1, 2}
	generic(t, search(nums, 3), -1)
}

func TestAnother(t *testing.T) {
	nums := []int{4, 5, 6, 7, 0, 1, 2}
	generic(t, search(nums, 0), 4)
}

func Test13And3(t *testing.T) {
	nums := []int{1, 3}
	generic(t, search(nums, 3), 1)
}

func Test351And3(t *testing.T) {
	nums := []int{3, 5, 1}
	generic(t, search(nums, 3), 0)
}

func Test51234And1(t *testing.T) {
	nums := []int{5, 1, 2, 3, 4}
	generic(t, search(nums, 1), 1)
}

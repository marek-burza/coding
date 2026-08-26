package lc035

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("SearchInsert - Expected %v, got %v!", expected, result)
	}
}

func TestEmptyAnd5(t *testing.T) {
	nums := []int{}
	generic(t, searchInsert(nums, 5), 0)
}

func Test1356And5(t *testing.T) {
	nums := []int{1, 3, 5, 6}
	generic(t, searchInsert(nums, 5), 2)
}

func Test1356And2(t *testing.T) {
	nums := []int{1, 3, 5, 6}
	generic(t, searchInsert(nums, 2), 1)
}

func Test1356And7(t *testing.T) {
	nums := []int{1, 3, 5, 6}
	generic(t, searchInsert(nums, 7), 4)
}

func Test1356And0(t *testing.T) {
	nums := []int{1, 3, 5, 6}
	generic(t, searchInsert(nums, 0), 0)
}

func Test1356And1(t *testing.T) {
	nums := []int{1, 3, 5, 6}
	generic(t, searchInsert(nums, 1), 0)
}

package lc220

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("ContainsNearbyAlmostDuplicate - Expected %v, got %v!", expected, result)
	}
}

func Test110202(t *testing.T) {
	nums := []int{1, 10, 20, 2}
	generic(t, containsNearbyAlmostDuplicate(nums, 3, 2), true)
}

func Test110204(t *testing.T) {
	nums := []int{1, 10, 20, 4}
	generic(t, containsNearbyAlmostDuplicate(nums, 3, 2), false)
}

func Test11020302(t *testing.T) {
	nums := []int{1, 10, 20, 30, 2}
	generic(t, containsNearbyAlmostDuplicate(nums, 3, 2), false)
}

func Test8715161915And1And3(t *testing.T) {
	nums := []int{8, 7, 15, 1, 6, 1, 9, 15}
	generic(t, containsNearbyAlmostDuplicate(nums, 1, 3), true)
}

func Test21474836402147483641And1And100(t *testing.T) {
	nums := []int{2147483640, 2147483641}
	generic(t, containsNearbyAlmostDuplicate(nums, 1, 100), true)
}

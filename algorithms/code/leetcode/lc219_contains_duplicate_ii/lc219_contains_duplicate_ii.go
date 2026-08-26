// Package lc219 implements https://leetcode.com/problems/contains-duplicate-ii/
package lc219

func containsNearbyDuplicate(nums []int, k int) bool {
	collected := make(map[int]struct{})
	var ordered []int
	for _, num := range nums {
		if _, found := collected[num]; found {
			return true
		}
		ordered = append(ordered, num)
		collected[num] = struct{}{}
		if len(ordered) > k {
			delete(collected, ordered[0])
			ordered = ordered[1:]
		}
	}
	return false
}

// Package lc217 implements https://leetcode.com/problems/contains-duplicate/
package lc217

func containsDuplicate(nums []int) bool {
	seen := make(map[int]struct{})
	for _, num := range nums {
		if _, found := seen[num]; found {
			return true
		}
		seen[num] = struct{}{}
	}
	return false
}

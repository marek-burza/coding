// Package lc128 implements https://leetcode.com/problems/longest-consecutive-sequence/
// #medium
package lc128

type ranged struct {
	a int
	z int
}

func longestConsecutive(nums []int) int {
	seen := make(map[int]struct{})
	mapped := make(map[int]*ranged)
	length := 0
	for _, num := range nums {
		if _, found := seen[num]; found {
			continue
		}
		seen[num] = struct{}{}
		ante, less := mapped[num-1]
		post, more := mapped[num+1]
		a := num
		z := num
		if less && more {
			a = ante.a
			z = post.z
		}
		if less {
			a = ante.a
		}
		if more {
			z = post.z
		}
		current := &ranged{a, z}
		mapped[a] = current
		mapped[z] = current
		span := z - a + 1
		length = max(length, span)
	}
	return length
	// This can be simplified by storing only the length of the range
	// in the hash table instead of range itself
}

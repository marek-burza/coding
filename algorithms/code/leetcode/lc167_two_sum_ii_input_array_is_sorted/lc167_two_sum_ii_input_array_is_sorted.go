// Package lc167 implements https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
package lc167

func twoSum(numbers []int, target int) []int {
	indices := make([]int, 2)
	if len(numbers) < 2 {
		return indices
	}
	a := 0
	z := len(numbers) - 1
	for a < z {
		v := numbers[a] + numbers[z]
		if v == target {
			indices[0] = a + 1
			indices[1] = z + 1
			break
		}
		if v > target {
			z--
		} else {
			a++
		}
	}
	return indices
}

// Package lc015 implements https://leetcode.com/problems/3sum/
package lc015

import (
	"sort"
)

func threeSum(nums []int) [][]int {
	sort.Ints(nums)
	var result [][]int
	length := len(nums)
	var previousNum *int
	for i, num := range nums {
		if previousNum != nil && num == *previousNum {
			continue
		}
		previousNum = &num
		j, k := i+1, length-1
		for j < k {
			if k < length-1 && nums[k] == nums[k+1] {
				k--
				continue
			}
			sum := num + nums[j] + nums[k]
			switch {
			case sum > 0:
				k--
			case sum < 0:
				j++
			default:
				result = append(result, []int{num, nums[j], nums[k]})
				j++
				k--
			}
		}
	}
	return result
}

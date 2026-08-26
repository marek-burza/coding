// Package lc377 implements https://leetcode.com/problems/combination-sum-iv/
// #medium
package lc377

func combinationSum4(nums []int, target int) int {
	cache := make([]int, target+1)
	cache[0] = 1
	for i := range target {
		if cache[i] == 0 {
			continue
		}
		for _, num := range nums {
			if i+num <= target {
				cache[i+num] += cache[i]
			}
		}
	}
	return cache[target]
}

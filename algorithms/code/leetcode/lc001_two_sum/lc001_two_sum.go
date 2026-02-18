// Package lc001 implements https://leetcode.com/problems/two-sum/
package lc001

func twoSum(nums []int, target int) []int {
	if nums == nil {
		return nil
	}
	numMap := make(map[int]int)
	for i, num := range nums {
		expected := target - num
		if index, found := numMap[expected]; found {
			return []int{index, i}
		}
		numMap[num] = i
	}
	return nil
}

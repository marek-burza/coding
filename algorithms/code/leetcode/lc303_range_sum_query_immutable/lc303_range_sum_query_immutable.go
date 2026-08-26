// Package lc303 implements https://leetcode.com/problems/range-sum-query-immutable/
package lc303

// NumArray Defines an array answering range sum queries
type NumArray struct {
	sums []int
}

// NewNumArray Creates the prefix sums of the given values
func NewNumArray(nums []int) *NumArray {
	array := &NumArray{sums: make([]int, len(nums))}
	summed := 0
	for i, numsI := range nums {
		summed += numsI
		array.sums[i] = summed
	}
	return array
}

// SumRange Returns the sum of the values between the two indices inclusive
func (array *NumArray) SumRange(left int, right int) int {
	summed := 0
	if left > 0 {
		summed = -array.sums[left-1]
	}
	summed += array.sums[right]
	return summed
}

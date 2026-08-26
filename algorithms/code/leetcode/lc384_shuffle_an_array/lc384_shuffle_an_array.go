// Package lc384 implements https://leetcode.com/problems/shuffle-an-array/
// #medium
package lc384

import "math/rand/v2"

// Solution Shuffles an array of values
type Solution struct {
	nums []int
}

// NewSolution Creates a shuffler over the given values
func NewSolution(nums []int) *Solution {
	return &Solution{nums: nums}
}

// Reset Returns the values in their original order
func (solution *Solution) Reset() []int {
	return append([]int{}, solution.nums...)
}

// Shuffle Returns the values in a random order
func (solution *Solution) Shuffle() []int {
	result := append([]int{}, solution.nums...)
	for i := len(solution.nums) - 1; i > 0; i-- {
		j := rand.IntN(i + 1)
		result[i], result[j] = result[j], result[i]
	}
	return result
}

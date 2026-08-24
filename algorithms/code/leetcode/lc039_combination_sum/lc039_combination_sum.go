// Package lc039 implements https://leetcode.com/problems/combination-sum/
// #medium
package lc039

func combinationSumInternal(candidates []int, target int, path []int, total int, index int, combos *[][]int) {
	if total == target {
		*combos = append(*combos, append([]int{}, path...))
		return
	}
	inner := append([]int{}, path...)
	partial := 0
	for partial <= target-total && index < len(candidates) {
		combinationSumInternal(candidates, target, inner, total+partial, index+1, combos)
		inner = append(inner, candidates[index])
		partial += candidates[index]
	}
}

func combinationSum(candidates []int, target int) [][]int {
	var combos [][]int
	combinationSumInternal(candidates, target, []int{}, 0, 0, &combos)
	return combos
}

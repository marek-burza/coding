// Package lc216 implements https://leetcode.com/problems/combination-sum-iii/
// #medium
package lc216

func traverse(contains int, summed int, left int, n int, found *[]int, start int) {
	if left == 0 && summed == n {
		*found = append(*found, contains)
	} else {
		for i := start; i < 10; i++ {
			mask := 1 << i
			// if (contains & mask) == 0 {
			traverse(contains|mask, summed+i, left-1, n, found, i+1)
		}
	}
}

func combinationSum3(k int, n int) [][]int {
	var found []int
	traverse(0, 0, k, n, &found, 1)
	var each [][]int
	for _, contains := range found {
		var entry []int
		for i := 1; i < 10; i++ {
			mask := 1 << i
			if (contains & mask) != 0 {
				entry = append(entry, i)
			}
		}
		each = append(each, entry)
	}
	return each
}

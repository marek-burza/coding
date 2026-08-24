// Package lc077 implements https://leetcode.com/problems/combinations/
// #medium
package lc077

func combineInternal(m int, n int, k int, prefix []int, found *[][]int) {
	for i := m; i < n-(k-1)+len(prefix)+1; i++ {
		prefix = append(prefix, i)
		if len(prefix) == k {
			*found = append(*found, append([]int{}, prefix...))
		} else {
			combineInternal(i+1, n, k, prefix, found)
		}
		prefix = prefix[:len(prefix)-1]
	}
}

func combine(n int, k int) [][]int {
	var found [][]int
	combineInternal(1, n, k, []int{}, &found)
	return found
}

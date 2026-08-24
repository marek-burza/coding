// Package lc096 implements https://leetcode.com/problems/unique-binary-search-trees/
// #medium
package lc096

func numTrees(n int) int {
	cache := make([]int, n+1)
	cache[0] = 1
	cache[1] = 1
	for i := 2; i <= n; i++ {
		for j := range i {
			cache[i] += cache[j] * cache[i-j-1]
		}
	}
	return cache[n]
}

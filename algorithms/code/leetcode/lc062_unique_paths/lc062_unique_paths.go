// Package lc062 implements https://leetcode.com/problems/unique-paths/
// #medium
package lc062

func nck(n int, k int) int {
	if k > n {
		return 0
	}
	if k*2 > n {
		k = n - k
	}
	if k == 0 {
		return 1
	}
	r := n
	for i := 2; i <= k; i++ {
		r *= n - i + 1
		r /= i
	}
	return r
}

func uniquePaths(m int, n int) int {
	m--
	return nck(m+n-1, m)
}

// Package lc274 implements https://leetcode.com/problems/h-index/
// #medium
package lc274

func hIndex(citations []int) int {
	n := len(citations)
	counts := make([]int, n+1)
	for _, citation := range citations {
		if citation > n {
			counts[n]++
		} else {
			counts[citation]++
		}
	}
	counted := 0
	i := n
	for {
		counted += counts[i]
		if counted >= i {
			return i
		}
		i--
	}
}

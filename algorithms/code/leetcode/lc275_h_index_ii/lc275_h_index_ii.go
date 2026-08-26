// Package lc275 implements https://leetcode.com/problems/h-index-ii/
// #medium
package lc275

func hIndex(citations []int) int {
	n := len(citations)
	a := 0
	z := n
	for a < z {
		m := (a + z) >> 1
		if citations[m] == n-m {
			return n - m
		}
		if citations[m] < n-m {
			a = m + 1
		} else {
			z = m
		}
	}
	return n - a
}

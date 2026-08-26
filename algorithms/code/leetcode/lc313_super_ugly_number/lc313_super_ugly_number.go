// Package lc313 implements https://leetcode.com/problems/super-ugly-number/
// #medium
package lc313

import "math"

func nthSuperUglyNumber(n int, primes []int) int {
	m := len(primes)
	mul := make([]int, m)
	dp := make([]int, n)
	dp[0] = 1
	for i := 1; i < n; i++ {
		dpI := math.MaxInt
		temp1 := -1
		for j := range m {
			temp2 := dp[mul[j]] * primes[j]
			if dpI > temp2 {
				dpI = temp2
				dp[i] = dpI
				temp1 = j
			} else if dpI == temp2 {
				mul[j]++
			}
		}
		mul[temp1]++
	}
	return dp[n-1]
}

// Package lc204 implements https://leetcode.com/problems/count-primes/
// #medium
package lc204

import "slices"

func countPrimes(n int) int {
	if n < 2 {
		return 0
	}
	// Eratosthenes sieve
	sieve := slices.Repeat([]bool{true}, n-2)
	count := 0
	for i := range sieve {
		if !sieve[i] {
			continue
		}
		count++
		number := 2 + i
		for j := i + number; j < len(sieve); j += number {
			sieve[j] = false
		}
	}
	return count
}

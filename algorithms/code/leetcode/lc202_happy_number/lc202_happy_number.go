// Package lc202 implements https://leetcode.com/problems/happy-number/
package lc202

func re(n int) int {
	result := 0
	for n != 0 {
		digit := n % 10
		n /= 10
		result += digit * digit
	}
	return result
}

func isHappy(n int) bool {
	seen := make(map[int]struct{})
	seen[n] = struct{}{}
	for n != 1 {
		n = re(n)
		if _, found := seen[n]; found {
			return false
		}
		seen[n] = struct{}{}
	}
	return true
}

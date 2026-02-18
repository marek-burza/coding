// Package lc009 implements https://leetcode.com/problems/palindrome-number/
package lc009

func isPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	xa := x
	xb := 0
	for x > 0 {
		xb = xb*10 + x%10
		x /= 10
	}
	return xa == xb
}

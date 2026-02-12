package lc009

// https://leetcode.com/problems/palindrome-number/

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

// Package lc058 implements https://leetcode.com/problems/length-of-last-word/
package lc058

func lengthOfLastWord(s string) int {
	if len(s) == 0 {
		return 0
	}
	n := len(s)
	for n > 0 && s[n-1] == ' ' {
		n--
	}
	for i := n - 1; i >= 0; i-- {
		if s[i] == ' ' {
			return n - i - 1
		}
	}
	return n
}

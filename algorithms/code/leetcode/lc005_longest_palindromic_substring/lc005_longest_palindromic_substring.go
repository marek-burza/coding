// Package lc005 implements https://leetcode.com/problems/longest-palindromic-substring/
// See also: Manacher algorithm
package lc005

func longestPalindrome(s string) string {
	longest := ""
	n := len(s)
	for index := 0; index < n; {
		// Find head & tail
		head, tail := index, index
		for tail+1 < n && s[head] == s[tail+1] {
			tail++
		}
		index += 1 + tail - head
		// Expand
		for 0 <= head-1 && tail+1 < n && s[head-1] == s[tail+1] {
			head--
			tail++
		}
		// Check
		if len(longest) < 1+tail-head {
			longest = s[head : 1+tail]
		}
	}
	return longest
}

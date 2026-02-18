// Package lc022 implements https://leetcode.com/problems/generate-parentheses/
package lc022

func generateParenthesisInternal(prefix string, standing, n int, found *[]string) {
	if n == 0 && standing == 0 {
		*found = append(*found, prefix)
		return
	}
	// open
	if n > 0 {
		generateParenthesisInternal(prefix+"(", standing+1, n-1, found)
	}
	// close
	if standing > 0 {
		generateParenthesisInternal(prefix+")", standing-1, n, found)
	}
}

func generateParenthesis(n int) []string {
	var found []string
	if n != 0 {
		generateParenthesisInternal("(", 1, n-1, &found)
	}
	return found
}

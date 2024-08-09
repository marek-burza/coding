package lc022

// https://leetcode.com/problems/generate-parentheses/

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

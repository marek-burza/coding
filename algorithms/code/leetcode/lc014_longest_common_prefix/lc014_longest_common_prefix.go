// Package lc014 implements https://leetcode.com/problems/longest-common-prefix/
package lc014

func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}
	for i := 0; i < len(strs[0]); i++ {
		char := strs[0][i]
		for _, str := range strs {
			if len(str) == 0 || i >= len(str) || str[i] != char {
				return strs[0][:i]
			}
		}
	}
	return strs[0]
}

// Package lc014 implements https://leetcode.com/problems/longest-common-prefix/
package lc014

func longestCommonPrefix(strs []string) string {
	if len(strs) > 0 {
		strs0 := strs[0]
		for i := 0; i < len(strs0); i++ {
			char := strs0[i]
			for _, str := range strs {
				if len(str) == 0 || i >= len(str) || str[i] != char {
					return strs0[:i]
				}
			}
		}
		return strs0
	}
	return ""
}

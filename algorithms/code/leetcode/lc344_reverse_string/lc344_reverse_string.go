// Package lc344 implements https://leetcode.com/problems/reverse-string/
package lc344

func reverseString(s []byte) {
	for i := range len(s) / 2 {
		s[i], s[len(s)-1-i] = s[len(s)-1-i], s[i]
	}
}

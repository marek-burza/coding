// Package lc125 implements https://leetcode.com/problems/valid-palindrome/
package lc125

import "unicode"

func isAlnum(character rune) bool {
	return unicode.IsLetter(character) || unicode.IsDigit(character)
}

func isPalindrome(s string) bool {
	if len(s) == 0 {
		return true
	}
	runes := []rune(s)
	i := 0
	j := len(runes) - 1
	for i <= j {
		a := runes[i]
		if !isAlnum(a) {
			i++
			continue
		}
		b := runes[j]
		if !isAlnum(b) {
			j--
			continue
		}
		if unicode.ToUpper(a) != unicode.ToUpper(b) {
			return false
		}
		i++
		j--
	}
	return true
}

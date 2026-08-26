// Package lc345 implements https://leetcode.com/problems/reverse-vowels-of-a-string/
package lc345

import "slices"

func isVowel(letter byte) bool {
	vowels := []byte{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
	return slices.Contains(vowels, letter)
}

func reverseVowels(s string) string {
	text := []byte(s)
	a := 0
	z := len(text) - 1
	for a < z {
		for a < len(text) && !isVowel(text[a]) {
			a++
		}
		for z >= 0 && !isVowel(text[z]) {
			z--
		}
		if a < z {
			text[a], text[z] = text[z], text[a]
			a++
			z--
		}
	}
	return string(text)
}

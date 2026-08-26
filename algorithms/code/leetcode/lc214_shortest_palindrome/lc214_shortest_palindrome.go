// Package lc214 implements https://leetcode.com/problems/shortest-palindrome/
package lc214

import "slices"

func reversed(s string) string {
	runes := []rune(s)
	slices.Reverse(runes)
	return string(runes)
}

func shortestPalindrome(s string) string {
	if len(s) == 0 {
		return s
	}
	a := s + reversed(s)
	cont := make([]int, len(a))
	for i := 1; i < len(a); i++ {
		index := cont[i-1]
		for index > 0 && a[index] != a[i] {
			index = cont[index-1]
		}
		cont[i] = index
		if a[index] == a[i] {
			cont[i]++
		}
	}
	return reversed(s[cont[len(cont)-1]:]) + s
}

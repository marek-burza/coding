package lc214

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("ShortestPalindrome - Expected %v, got %v!", expected, result)
	}
}

func TestAacecaaa(t *testing.T) {
	generic(t, shortestPalindrome("aacecaaa"), "aaacecaaa")
}

func TestAbcd(t *testing.T) {
	generic(t, shortestPalindrome("abcd"), "dcbabcd")
}

func TestNothing(t *testing.T) {
	generic(t, shortestPalindrome(""), "")
}

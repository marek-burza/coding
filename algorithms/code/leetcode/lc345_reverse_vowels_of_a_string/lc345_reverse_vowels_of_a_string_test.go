package lc345

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("ReverseVowels - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	generic(t, reverseVowels("hello"), "holle")
}

func TestExample2(t *testing.T) {
	generic(t, reverseVowels("leotcede"), "leetcode")
}

func TestOe(t *testing.T) {
	generic(t, reverseVowels("OE"), "EO")
}

func TestZt(t *testing.T) {
	generic(t, reverseVowels("zt"), "zt")
}

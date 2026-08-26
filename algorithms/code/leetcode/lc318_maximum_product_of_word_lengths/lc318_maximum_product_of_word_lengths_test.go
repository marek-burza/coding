package lc318

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MaxProduct - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	words := []string{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}
	generic(t, maxProduct(words), 16)
}

func TestExample2(t *testing.T) {
	words := []string{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}
	generic(t, maxProduct(words), 4)
}

func TestExample3(t *testing.T) {
	words := []string{"a", "aa", "aaa", "aaaa"}
	generic(t, maxProduct(words), 0)
}

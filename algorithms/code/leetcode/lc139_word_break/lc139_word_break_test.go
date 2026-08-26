package lc139

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("WordBreak - Expected %v, got %v!", expected, result)
	}
}

func TestAAndA(t *testing.T) {
	generic(t, wordBreak("a", []string{"a"}), true)
}

func TestOther(t *testing.T) {
	generic(t, wordBreak("catsandog", []string{"cats", "dog", "sand", "and", "cat"}), false)
}

func TestAnother(t *testing.T) {
	generic(t, wordBreak("leetcode", []string{"leet", "code"}), true)
}

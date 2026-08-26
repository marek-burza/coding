package lc383

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("CanConstruct - Expected %v, got %v!", expected, result)
	}
}

func TestExampleAAndB(t *testing.T) {
	generic(t, canConstruct("a", "b"), false)
}

func TestExampleAaAndAb(t *testing.T) {
	generic(t, canConstruct("aa", "ab"), false)
}

func TestExampleAaAndAab(t *testing.T) {
	generic(t, canConstruct("aa", "aab"), true)
}

package lc205

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsIsomorphic - Expected %v, got %v!", expected, result)
	}
}

func TestAaAndAb(t *testing.T) {
	generic(t, isIsomorphic("aa", "ab"), false)
}

func TestEggAndAdd(t *testing.T) {
	generic(t, isIsomorphic("egg", "add"), true)
}

func TestAcAndBb(t *testing.T) {
	generic(t, isIsomorphic("ac", "bb"), false)
}

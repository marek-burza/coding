package lc060

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("GetPermutation - Expected %v, got %v!", expected, result)
	}
}

func Test21(t *testing.T) {
	generic(t, getPermutation(2, 1), "12")
}

func Test32(t *testing.T) {
	generic(t, getPermutation(3, 2), "132")
}

func TestNothing(t *testing.T) {
	generic(t, getPermutation(1, -1), "")
	generic(t, getPermutation(-1, 1), "")
}

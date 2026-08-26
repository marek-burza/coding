package lc242

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsAnagram - Expected %v, got %v!", expected, result)
	}
}

func TestAaAndA(t *testing.T) {
	generic(t, isAnagram("aa", "a"), false)
}

func TestAAndB(t *testing.T) {
	generic(t, isAnagram("a", "b"), false)
}

func TestAnagramAndNagaram(t *testing.T) {
	generic(t, isAnagram("anagram", "nagaram"), true)
}

func TestRatAndCar(t *testing.T) {
	generic(t, isAnagram("rat", "car"), false)
}

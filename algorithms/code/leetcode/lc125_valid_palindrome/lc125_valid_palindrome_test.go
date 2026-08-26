package lc125

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsPalindrome - Expected %v, got %v!", expected, result)
	}
}

func TestAManAPlanACanalPanama(t *testing.T) {
	generic(t, isPalindrome("A man, a plan, a canal: Panama"), true)
}

func TestRaceACar(t *testing.T) {
	generic(t, isPalindrome("race a car"), false)
}

func TestAva(t *testing.T) {
	generic(t, isPalindrome("Ava"), true)
}

func TestBurger(t *testing.T) {
	generic(t, isPalindrome("burger"), false)
}

func TestNothing(t *testing.T) {
	generic(t, isPalindrome(""), true)
}

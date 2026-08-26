package lc290

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("WordPattern - Expected %v, got %v!", expected, result)
	}
}

func TestAbbaAndDogCatCatDog(t *testing.T) {
	generic(t, wordPattern("abba", "dog cat cat dog"), true)
}

func TestAbbaAndDogCatCatFish(t *testing.T) {
	generic(t, wordPattern("abba", "dog cat cat fish"), false)
}

func TestAaaaAndDogCatCatDog(t *testing.T) {
	generic(t, wordPattern("aaaa", "dog cat cat dog"), false)
}

func TestAbbaAndDogDogDogDog(t *testing.T) {
	generic(t, wordPattern("abba", "dog dog dog dog"), false)
}

func TestAbAndBC(t *testing.T) {
	generic(t, wordPattern("ab", "b c"), true)
}

func TestMismatched(t *testing.T) {
	generic(t, wordPattern("ab", "c"), false)
}

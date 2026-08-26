package lc058

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("LengthOfLastWord - Expected %v, got %v!", expected, result)
	}
}

func TestHelloWorld(t *testing.T) {
	generic(t, lengthOfLastWord("Hello World"), 5)
}

func TestNothing(t *testing.T) {
	generic(t, lengthOfLastWord(""), 0)
}

func TestAlmostNothing(t *testing.T) {
	generic(t, lengthOfLastWord(" "), 0)
}

func TestTrailingSpace(t *testing.T) {
	generic(t, lengthOfLastWord("Hello World  "), 5)
}

func TestSingleWord(t *testing.T) {
	generic(t, lengthOfLastWord("HelloWorld"), 10)
}

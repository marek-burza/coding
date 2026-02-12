package lc003

import (
	"testing"
)

func TestSubstring1(t *testing.T) {
	result := lengthOfLongestSubstring("bcabcbb")
	expected := 3
	if result != expected {
		t.Errorf("LengthOfLongestSubstring - Expected %v, but got %v!", expected, result)
	}
}

func TestSubstring2(t *testing.T) {
	result := lengthOfLongestSubstring("bbbbb")
	expected := 1
	if result != expected {
		t.Errorf("LengthOfLongestSubstring - Expected %v, but got %v!", expected, result)
	}
}

func TestSubstring3(t *testing.T) {
	result := lengthOfLongestSubstring("dvdf")
	expected := 3
	if result != expected {
		t.Errorf("LengthOfLongestSubstring - Expected %v, but got %v!", expected, result)
	}
}

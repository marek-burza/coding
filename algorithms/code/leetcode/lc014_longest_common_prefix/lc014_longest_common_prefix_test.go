package lc014

import (
	"testing"
)

func TestAlaAndAlaMaKota(t *testing.T) {
	result := longestCommonPrefix([]string{"Ala", "Ala Ma Kota"})
	expected := "Ala"
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

func TestAaA(t *testing.T) {
	result := longestCommonPrefix([]string{"aa", "a"})
	expected := "a"
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

func TestAbAaCoverage(t *testing.T) {
	result := longestCommonPrefix([]string{"ab", "aa"})
	expected := "a"
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

func TestNone(t *testing.T) {
	result := longestCommonPrefix([]string{})
	expected := ""
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

func TestEmptyB(t *testing.T) {
	result := longestCommonPrefix([]string{"", "b"})
	expected := ""
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

func TestNullB(t *testing.T) {
	result := longestCommonPrefix([]string{"", "b"})
	expected := ""
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

func TestSame(t *testing.T) {
	result := longestCommonPrefix([]string{"same", "same"})
	expected := "same"
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

func TestNull(t *testing.T) {
	result := longestCommonPrefix(nil)
	expected := ""
	if result != expected {
		t.Errorf("LongestCommonPrefix - Expected %s, but got %s!", expected, result)
	}
}

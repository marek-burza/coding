package lc005

import (
	"testing"
)

func Test1(t *testing.T) {
	result := longestPalindrome("babad")
	expected := "bab"
	if result != expected && result != "aba" {
		t.Errorf("LongestPalindrome - Expected %v or 'aba', but got %v!", expected, result)
	}
}

func Test2(t *testing.T) {
	result := longestPalindrome("cbbd")
	expected := "bb"
	if result != expected {
		t.Errorf("LongestPalindrome - Expected %v, but got %v!", expected, result)
	}
}

func Test3(t *testing.T) {
	result := longestPalindrome("a")
	expected := "a"
	if result != expected {
		t.Errorf("LongestPalindrome - Expected %v, but got %v!", expected, result)
	}
}

func Test4(t *testing.T) {
	result := longestPalindrome("ac")
	expected := "a"
	if result != expected && result != "c" {
		t.Errorf("LongestPalindrome - Expected %v or 'c', but got %v!", expected, result)
	}
}

func TestBb(t *testing.T) {
	result := longestPalindrome("bb")
	expected := "bb"
	if result != expected {
		t.Errorf("LongestPalindrome - Expected %v, but got %v!", expected, result)
	}
}

func TestLonger(t *testing.T) {
	result := longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth")
	expected := "ranynar"
	if result != expected {
		t.Errorf("LongestPalindrome - Expected %v, but got %v!", expected, result)
	}
}

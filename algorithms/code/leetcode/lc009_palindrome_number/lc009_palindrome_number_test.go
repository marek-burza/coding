package lc009

import (
	"testing"
)

func Test213(t *testing.T) {
	if result := isPalindrome(213); result != false {
		t.Errorf("IsPalindrome - Expected false, but got %v!", result)
	}
}

func Test456(t *testing.T) {
	if result := isPalindrome(456); result != false {
		t.Errorf("IsPalindrome - Expected false, but got %v!", result)
	}
}

func Test454(t *testing.T) {
	if result := isPalindrome(454); result != true {
		t.Errorf("IsPalindrome - Expected true, but got %v!", result)
	}
}

func Test99(t *testing.T) {
	if result := isPalindrome(99); result != true {
		t.Errorf("IsPalindrome - Expected true, but got %v!", result)
	}
}

func Test1(t *testing.T) {
	if result := isPalindrome(1); result != true {
		t.Errorf("IsPalindrome - Expected true, but got %v!", result)
	}
}

func Test10(t *testing.T) {
	if result := isPalindrome(10); result != false {
		t.Errorf("IsPalindrome - Expected false, but got %v!", result)
	}
}

func TestMinus1(t *testing.T) {
	if result := isPalindrome(-1); result != false {
		t.Errorf("IsPalindrome - Expected false, but got %v!", result)
	}
}

func Test0(t *testing.T) {
	if result := isPalindrome(0); result != true {
		t.Errorf("IsPalindrome - Expected true, but got %v!", result)
	}
}

package lc171

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("TitleToNumber - Expected %v, got %v!", expected, result)
	}
}

func TestA(t *testing.T) {
	generic(t, titleToNumber("A"), 1)
}

func TestB(t *testing.T) {
	generic(t, titleToNumber("B"), 2)
}

func TestC(t *testing.T) {
	generic(t, titleToNumber("C"), 3)
}

func TestZ(t *testing.T) {
	generic(t, titleToNumber("Z"), 26)
}

func TestAA(t *testing.T) {
	generic(t, titleToNumber("AA"), 27)
}

func TestAB(t *testing.T) {
	generic(t, titleToNumber("AB"), 28)
}

func TestNothing(t *testing.T) {
	generic(t, titleToNumber(""), -1)
}

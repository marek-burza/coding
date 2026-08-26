package lc168

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("ConvertToTitle - Expected %v, got %v!", expected, result)
	}
}

func Test1(t *testing.T) {
	generic(t, convertToTitle(1), "A")
}

func Test2(t *testing.T) {
	generic(t, convertToTitle(2), "B")
}

func Test3(t *testing.T) {
	generic(t, convertToTitle(3), "C")
}

func Test26(t *testing.T) {
	generic(t, convertToTitle(26), "Z")
}

func Test27(t *testing.T) {
	generic(t, convertToTitle(27), "AA")
}

func Test28(t *testing.T) {
	generic(t, convertToTitle(28), "AB")
}

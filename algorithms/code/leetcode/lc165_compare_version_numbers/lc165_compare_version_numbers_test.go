package lc165

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("CompareVersion - Expected %v, got %v!", expected, result)
	}
}

func Test1And1(t *testing.T) {
	generic(t, compareVersion("1", "1"), 0)
}

func Test1And10(t *testing.T) {
	generic(t, compareVersion("1", "1.0"), 0)
}

func Test2And1(t *testing.T) {
	generic(t, compareVersion("2", "1"), 1)
}

func Test1And131(t *testing.T) {
	generic(t, compareVersion("1", "13.1"), -1)
}

func Test101And1(t *testing.T) {
	generic(t, compareVersion("1.0.1", "1"), 1)
}

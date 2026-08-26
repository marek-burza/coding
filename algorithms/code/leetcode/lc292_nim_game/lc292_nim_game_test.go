package lc292

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("CanWinNim - Expected %v, got %v!", expected, result)
	}
}

func Test1To10(t *testing.T) {
	// +  1 o
	generic(t, canWinNim(1), true)
	// +  2 o o
	generic(t, canWinNim(2), true)
	// +  3 o o o
	generic(t, canWinNim(3), true)
	// -  4 o ? ? x
	generic(t, canWinNim(4), false)
	// +  5 o x ? ? o
	generic(t, canWinNim(5), true)
	// +  6 o o x ? ? o
	generic(t, canWinNim(6), true)
	// +  7 o o o x ? ? o
	generic(t, canWinNim(7), true)
	// -  8 o ? ? . . . . x     (leads to 7, 6 or 5)
	generic(t, canWinNim(8), false)
	// +  9 o x ? ? . . . . o   (leads to 8)
	generic(t, canWinNim(9), true)
	// + 10 o o x ? ? . . . . o (leads to 8)
	generic(t, canWinNim(10), true)
}

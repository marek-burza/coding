package lc202

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsHappy - Expected %v, got %v!", expected, result)
	}
}

func Test19(t *testing.T) {
	generic(t, isHappy(19), true)
}

func Test2(t *testing.T) {
	generic(t, isHappy(2), false)
}

package lc278

import "testing"

func generic(t *testing.T, n int, expected int) {
	lc278FirstBadVersion = expected
	result := firstBadVersion(n)
	if expected != result {
		t.Errorf("FirstBadVersion - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	generic(t, 8000, 456)
}

func TestBigExample(t *testing.T) {
	generic(t, 2126753390, 1702766719)
}

func TestSmallExample(t *testing.T) {
	generic(t, 1, 1)
}

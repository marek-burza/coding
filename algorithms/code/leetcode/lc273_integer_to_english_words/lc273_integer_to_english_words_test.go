package lc273

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("NumberToWords - Expected %v, got %v!", expected, result)
	}
}

func Test123(t *testing.T) {
	generic(t, numberToWords(123), "One Hundred Twenty Three")
}

func Test12345(t *testing.T) {
	generic(t, numberToWords(12345), "Twelve Thousand Three Hundred Forty Five")
}

func Test1234567(t *testing.T) {
	expected := "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
	generic(t, numberToWords(1234567), expected)
}

func Test91(t *testing.T) {
	generic(t, numberToWords(91), "Ninety One")
}

func Test19(t *testing.T) {
	generic(t, numberToWords(19), "Nineteen")
}

func Test100(t *testing.T) {
	generic(t, numberToWords(100), "One Hundred")
}

func Test0(t *testing.T) {
	generic(t, numberToWords(0), "Zero")
}

func Test1000(t *testing.T) {
	generic(t, numberToWords(1000), "One Thousand")
}

func Test20(t *testing.T) {
	generic(t, numberToWords(20), "Twenty")
}

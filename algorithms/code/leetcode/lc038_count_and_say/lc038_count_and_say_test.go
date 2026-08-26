package lc038

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("CountAndSay - Expected %v, got %v!", expected, result)
	}
}

func Test1(t *testing.T) {
	generic(t, countAndSay(1), "1")
}

func Test2(t *testing.T) {
	generic(t, countAndSay(2), "11")
}

func Test3(t *testing.T) {
	generic(t, countAndSay(3), "21")
}

func Test4(t *testing.T) {
	generic(t, countAndSay(4), "1211")
}

func Test5(t *testing.T) {
	generic(t, countAndSay(5), "111221")
}

func Test0(t *testing.T) {
	generic(t, countAndSay(0), "")
}

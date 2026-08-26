package lc387

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("FirstUniqChar - Expected %v, got %v!", expected, result)
	}
}

func TestLeetcode(t *testing.T) {
	generic(t, firstUniqChar("leetcode"), 0)
}

func TestLoveleetcode(t *testing.T) {
	generic(t, firstUniqChar("loveleetcode"), 2)
}

func TestEmpty(t *testing.T) {
	generic(t, firstUniqChar(""), -1)
}

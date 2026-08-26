package lc331

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsValidSerialization - Expected %v, got %v!", expected, result)
	}
}

func TestEmpty(t *testing.T) {
	generic(t, isValidSerialization("#"), true)
}

func TestEmptyButNotReally(t *testing.T) {
	generic(t, isValidSerialization("#1"), false)
}

func TestExample1(t *testing.T) {
	generic(t, isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"), true)
}

func TestExample2(t *testing.T) {
	generic(t, isValidSerialization("1,#"), false)
}

func TestExample3(t *testing.T) {
	generic(t, isValidSerialization("9,#,#,1"), false)
}

func TestNothing(t *testing.T) {
	generic(t, isValidSerialization(""), false)
}

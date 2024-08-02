package lc028

import (
	"testing"
)

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("StrStr - Expected %v, got %v!", expected, result)
	}
}

func TestEmpty(t *testing.T) {
    expected := 0
    result := StrStr("", "")
    generic(t, result, expected)
}

func TestMississippiA(t *testing.T) {
    expected := -1
    result := StrStr("mississippi", "a")
    generic(t, result, expected)
}

func TestMississippiSi(t *testing.T) {
    expected := 3
    result := StrStr("mississippi", "si")
    generic(t, result, expected)
}

func TestBiggerInSmaller(t *testing.T) {
    expected := -1
    result := StrStr("", "test")
    generic(t, result, expected)
}

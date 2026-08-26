package lc344

import "testing"

func TestExample(t *testing.T) {
	s := []byte("hello")
	reverseString(s)
	expected := "olleh"
	if expected != string(s) {
		t.Errorf("ReverseString - Expected %v, got %v!", expected, string(s))
	}
}

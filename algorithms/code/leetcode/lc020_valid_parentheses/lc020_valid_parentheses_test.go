package lc020

import (
	"testing"
)

func generic(t *testing.T, input string, expected bool) {
	result := isValid(input)
	if result != expected {
		t.Errorf("IsValid(%v) - expected %v, got %v!", input, expected, result)
	}
}

func TestGarbage(t *testing.T) {
	generic(t, "*", false)
}

func TestRB(t *testing.T) {
	generic(t, "(", false)
}

func TestRBRE(t *testing.T) {
	generic(t, "()", true)
}

func TestRBRESBSECBCE(t *testing.T) {
	generic(t, "()[]{}", true)
}

func TestRBSBRESE(t *testing.T) {
	generic(t, "([)]", false)
}

func TestRBCBRECE(t *testing.T) {
	generic(t, "({)}", false)
}

func TestSBRBSERE(t *testing.T) {
	generic(t, "[(])", false)
}

func TestCBRBCERE(t *testing.T) {
	generic(t, "{(})", false)
}

func TestRE(t *testing.T) {
	generic(t, ")", false)
}

func TestSE(t *testing.T) {
	generic(t, "]", false)
}

func TestCE(t *testing.T) {
	generic(t, "}", false)
}

func TestNothing(t *testing.T) {
	generic(t, "", true)
}

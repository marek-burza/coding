package gfg666

import (
	"testing"
)

func TestResultPresent(t *testing.T) {
	result := A(1)
	if result != 1 {
		t.Errorf("Evil!")
	}
}

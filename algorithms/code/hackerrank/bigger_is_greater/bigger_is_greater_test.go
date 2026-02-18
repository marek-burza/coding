package biggerisgreater

import (
	"testing"
)

func TestAb(t *testing.T) {
	if BiggerIsGreater("ab") != "ba" {
		t.Errorf("BiggerIsGreater failed with ab!")
	}
}

func TestBb(t *testing.T) {
	if BiggerIsGreater("bb") != "no answer" {
		t.Errorf("BiggerIsGreater failed with bb!")
	}
}

func TestHefg(t *testing.T) {
	if BiggerIsGreater("hefg") != "hegf" {
		t.Errorf("BiggerIsGreater failed with hefg!")
	}
}

func TestDhck(t *testing.T) {
	if BiggerIsGreater("dhck") != "dhkc" {
		t.Errorf("BiggerIsGreater failed with dhck!")
	}
}

func TestDkhc(t *testing.T) {
	if BiggerIsGreater("dkhc") != "hcdk" {
		t.Errorf("BiggerIsGreater failed with dkhc %v!", BiggerIsGreater("dkhc"))
	}
}

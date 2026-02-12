package biggerisgreater

import (
	"testing"
)

func TestAb(t *testing.T) {
	if "ba" != BiggerIsGreater("ab") {
		t.Errorf("BiggerIsGreater failed with ab!")
	}
}

func TestBb(t *testing.T) {
	if "no answer" != BiggerIsGreater("bb") {
		t.Errorf("BiggerIsGreater failed with bb!")
	}
}

func TestHefg(t *testing.T) {
	if "hegf" != BiggerIsGreater("hefg") {
		t.Errorf("BiggerIsGreater failed with hefg!")
	}
}

func TestDhck(t *testing.T) {
	if "dhkc" != BiggerIsGreater("dhck") {
		t.Errorf("BiggerIsGreater failed with dhck!")
	}
}

func TestDkhc(t *testing.T) {
	if "hcdk" != BiggerIsGreater("dkhc") {
		t.Errorf("BiggerIsGreater failed with dkhc %v!", BiggerIsGreater("dkhc"))
	}
}

package codilityequi

import (
	"testing"
)

func TestExample(t *testing.T) {
	result := FindEquilibriumIndex([]int{-1, 3, -4, 5, 1, -6, 2, 1})
	if result != 1 {
		t.Errorf("LockerDistances returns wrong value!")
	}
}

func TestEmpty(t *testing.T) {
	result := FindEquilibriumIndex([]int{})
	if result != -1 {
		t.Errorf("LockerDistances returns wrong value!")
	}
}

func TestInvalid(t *testing.T) {
	result := FindEquilibriumIndex([]int{1, 2, 3, 4})
	if result != -1 {
		t.Errorf("LockerDistances returns wrong value!")
	}
}

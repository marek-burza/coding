package lc007

import (
	"testing"
)

func Test2000000002(t *testing.T) {
	if result := reverse(2000000002); result != 2000000002 {
		t.Errorf("Reverse - Expected 2000000002, but got %v!", result)
	}
}

func TestMinus2147483648(t *testing.T) {
	if result := reverse(-2147483648); result != 0 {
		t.Errorf("Reverse - Expected 0, but got %v!", result)
	}
}

func Test1000000003(t *testing.T) {
	if result := reverse(1000000003); result != 0 {
		t.Errorf("Reverse - Expected 0, but got %v!", result)
	}
}

func Test1534236469(t *testing.T) {
	if result := reverse(1534236469); result != 0 {
		t.Errorf("Reverse - Expected 0, but got %v!", result)
	}
}

func TestMinus321(t *testing.T) {
	if result := reverse(-321); result != -123 {
		t.Errorf("Reverse - Expected -123, but got %v!", result)
	}
}

package lc006

import (
	"testing"
)

func Test1(t *testing.T) {
	result := convert("PAYPALISHIRING", 3)
	expected := "PAHNAPLSIIGYIR"
	if result != expected {
		t.Errorf("Convert - Expected %v, but got %v!", expected, result)
	}
}

func Test2(t *testing.T) {
	result := convert("PAYPALISHIRING", 4)
	expected := "PINALSIGYAHRPI"
	if result != expected {
		t.Errorf("Convert - Expected %v, but got %v!", expected, result)
	}
}

func Test3(t *testing.T) {
	result := convert("A", 1)
	expected := "A"
	if result != expected {
		t.Errorf("Convert - Expected %v, but got %v!", expected, result)
	}
}

func TestAbcd(t *testing.T) {
	result := convert("ABCD", 3)
	expected := "ABDC"
	if result != expected {
		t.Errorf("Convert - Expected %v, but got %v!", expected, result)
	}
}

func TestAbc(t *testing.T) {
	result := convert("ABC", 2)
	expected := "ACB"
	if result != expected {
		t.Errorf("Convert - Expected %v, but got %v!", expected, result)
	}
}

func TestNothing(t *testing.T) {
	result := convert("", 2)
	if result != "" {
		t.Errorf("Convert - Expected an empty string, but got %v!", result)
	}
}

func TestZero(t *testing.T) {
	result := convert("A", 0)
	if result != "" {
		t.Errorf("Convert - Expected an empty string, but got %v!", result)
	}
}

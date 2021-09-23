package acmicpcteam

import (
	"testing"
)

func TestExample1(t *testing.T) {
	result := AcmTeam([]string{"10101", "11100", "11010", "00101"})
	if result[0] != 5 || result[1] != 2 {
		t.Errorf("AcmTeam returns wrong value for example 1!")
	}
}

func TestExample2(t *testing.T) {
	result := AcmTeam([]string{"10101", "11110", "00010"})
	if result[0] != 5 || result[1] != 1 {
		t.Errorf("AcmTeam returns wrong value for example 2!")
	}
}

package lc134

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("CanCompleteCircuit - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	gas := []int{99, 99, 99, 104}
	cost := []int{100, 100, 100, 100}
	generic(t, canCompleteCircuit(gas, cost), 3)
}

func TestOther(t *testing.T) {
	gas := []int{1, 2, 3, 4, 5}
	cost := []int{3, 4, 5, 1, 2}
	generic(t, canCompleteCircuit(gas, cost), 3)
}

func TestAnother(t *testing.T) {
	gas := []int{1, 2, 3}
	cost := []int{3, 4, 3}
	generic(t, canCompleteCircuit(gas, cost), -1)
}

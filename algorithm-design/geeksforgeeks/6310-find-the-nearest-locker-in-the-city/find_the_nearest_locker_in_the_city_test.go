package gfg6310

import (
	"math"
	"testing"
)

func TestBadSize(t *testing.T) {
	result := LockerDistances(-1, -1, nil)
	if result != nil {
		t.Errorf("LockerDistances returns nil!")
	}
}

func TestResultPresent(t *testing.T) {
	result := LockerDistances(1, 1, nil)
	if result == nil {
		t.Errorf("LockerDistances returns nil!")
		return
	}
	if result[0][0] != math.MaxUint64 {
		t.Errorf("LockerDistances returns invalid result when there are no lockers!")
	}
}

func TestResultJustLocker(t *testing.T) {
	var lockers = []Coordinates{
		Coordinates{x: 0, y: 0},
	}
	result := LockerDistances(1, 1, lockers)
	if result == nil {
		t.Errorf("LockerDistances returns nil!")
		return
	}
	if result[0][0] != 0 {
		t.Errorf("LockerDistances returns invalid result when there is just one locker!")
	}
}

func TestExample1(t *testing.T) {
	var lockers = []Coordinates{
		Coordinates{x: 0, y: 0},
	}
	result := LockerDistances(3, 5, lockers)
	expected := [][]uint64{
		{0, 1, 2, 3, 4},
		{1, 2, 3, 4, 5},
		{2, 3, 4, 5, 6},
	}
	if result == nil {
		t.Errorf("LockerDistances returns nil!")
		return
	}
	if len(expected) != len(result) {
		t.Errorf("LockerDistances returns grid with incorrect numer of rows!")
		return
	}
	for row := 0; row < len(expected); row++ {
		if len(expected[row]) != len(result[row]) {
			t.Errorf("LockerDistances returns grid with incorrect numer of columns!")
		}
		for column := 0; column < len(expected[row]); column++ {
			if expected[row][column] != result[row][column] {
				t.Errorf("LockerDistances returns incorrect value!")
			}
		}
	}
}

func TestExample2(t *testing.T) {
	var lockers = []Coordinates{
		Coordinates{x: 1, y: 2},
		Coordinates{x: 3, y: 6},
	}
	result := LockerDistances(5, 7, lockers)
	expected := [][]uint64{
		{3, 2, 1, 2, 3, 4, 3},
		{2, 1, 0, 1, 2, 3, 2},
		{3, 2, 1, 2, 3, 2, 1},
		{4, 3, 2, 3, 2, 1, 0},
		{5, 4, 3, 4, 3, 2, 1},
	}
	if result == nil {
		t.Errorf("LockerDistances returns nil!")
		return
	}
	if len(expected) != len(result) {
		t.Errorf("LockerDistances returns grid with incorrect numer of rows!")
		return
	}
	for row := 0; row < len(expected); row++ {
		if len(expected[row]) != len(result[row]) {
			t.Errorf("LockerDistances returns grid with incorrect numer of columns!")
		}
		for column := 0; column < len(expected[row]); column++ {
			if expected[row][column] != result[row][column] {
				t.Errorf("LockerDistances returns incorrect value!")
			}
		}
	}
}

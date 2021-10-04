package gfg7094

import (
	"testing"
)

var network = Network{
	map[string][]string{
		"Jack":  []string{"Jane", "John"},
		"John":  []string{"Alice", "Jack", "Jane"},
		"Alice": []string{"Bob"},
		"Bob":   []string{"Alice", "Jane"},
		"Jane":  []string{"Jack", "John"},
		"Loner": []string{},
	},
	map[string][]string{
		"Jack":  []string{"Science 1"},
		"John":  []string{"Science 1", "Arts"},
		"Alice": []string{"Science 1", "Science 2", "Arts"},
		"Bob":   []string{"Arts", "Sports"},
		"Jane":  []string{"Science 1", "Science 2", "Arts", "Sports"},
		"Loner": []string{"Philosophy"},
	},
}

func TestLoner(t *testing.T) {
	expected := []string{}
	result := (&network).GetRankedCourses("Loner")
	if result == nil {
		t.Errorf("GetRankedCourses returns nil slice!")
		return
	}
	if len(expected) != len(result) {
		t.Errorf("GetRankedCourses returns slice with incorrect length!")
		return
	}
	for i := 0; i < len(expected); i++ {
		if expected[i] != result[i] {
			t.Errorf("GetRankedCourses returns wrong values in slice!")
		}
	}
}

func TestJack(t *testing.T) {
	expected := []string{"Arts", "Science 2", "Sports"}
	result := (&network).GetRankedCourses("Jack")
	if result == nil {
		t.Errorf("GetRankedCourses returns nil slice!")
		return
	}
	if len(expected) != len(result) {
		t.Errorf("GetRankedCourses returns slice with incorrect length!")
		return
	}
	for i := 0; i < len(expected); i++ {
		if expected[i] != result[i] {
			t.Errorf("GetRankedCourses returns wrong values in slice!")
		}
	}
}

func TestJane(t *testing.T) {
	expected := []string{}
	result := (&network).GetRankedCourses("Jane")
	if result == nil {
		t.Errorf("GetRankedCourses returns nil slice!")
		return
	}
	if len(expected) != len(result) {
		t.Errorf("GetRankedCourses returns slice with incorrect length!")
		return
	}
	for i := 0; i < len(expected); i++ {
		if expected[i] != result[i] {
			t.Errorf("GetRankedCourses returns wrong values in slice!")
		}
	}
}

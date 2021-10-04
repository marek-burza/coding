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

var other = Network{
	map[string][]string{
		"Student1": []string{"Student2", "Student3"},
		"Student2": []string{"Student1", "Student3"},
		"Student3": []string{"Student1", "Student2"},
	},
	map[string][]string{
		"Student1": []string{"Course1"},
		"Student2": []string{"Course2"},
		"Student3": []string{"Course3"},
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

func TestLeftovers(t *testing.T) {
	result1 := (&network).GetAttendedCoursesForUser("")
	if 0 != len(result1) {
		t.Errorf("GetAttendedCoursesForUser returns incorrect value for unknown student!")
		return
	}
	result2 := (&network).GetDirectFriendsForUser("")
	if 0 != len(result2) {
		t.Errorf("GetDirectFriendsForUser returns incorrect value for unknown student!")
		return
	}
	result3 := (&other).GetRankedCourses("Student1")
	if 2 != len(result3) {
		t.Errorf("GetRankedCourses returns incorrect result for an even network!")
		return
	}
}

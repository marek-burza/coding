package angryprofessor

import (
	"testing"
)

type testDescription struct {
	k        int
	a        []int
	expected string
}

func testRunner(t *testing.T, tests []testDescription) {
	for i, description := range tests {
		result := AngryProfessor(description.k, description.a)
		if result != description.expected {
			t.Errorf("AngryProfessor test %v!", i)
		}
	}
}

func TestExample(t *testing.T) {
	tests := []testDescription{
		testDescription{k: 3, a: []int{-1, -3, 4, 2}, expected: "YES"},
		testDescription{k: 2, a: []int{0, -1, 2, 1}, expected: "NO"},
	}
	testRunner(t, tests)
}

func Test01(t *testing.T) {
	tests := []testDescription{
		testDescription{k: 4, a: []int{-93, -86, 49, -62, -90, -63, 40, 72, 11, 67}, expected: "NO"},
		testDescription{k: 10, a: []int{23, -35, -2, 58, -67, -56, -42, -73, -19, 37}, expected: "YES"},
		testDescription{k: 9, a: []int{13, 91, 56, -62, 96, -5, -84, -36, -46, -13}, expected: "YES"},
		testDescription{k: 8, a: []int{45, 67, 64, -50, -8, 78, 84, -51, 99, 60}, expected: "YES"},
		testDescription{k: 7, a: []int{26, 94, -95, 34, 67, -97, 17, 52, 1, 86}, expected: "YES"},
		testDescription{k: 2, a: []int{19, 29, -17, -71, -75, -27, -56, -53, 65, 83}, expected: "NO"},
		testDescription{k: 10, a: []int{-32, -3, -70, 8, -40, -96, -18, -46, -21, -79}, expected: "YES"},
		testDescription{k: 9, a: []int{-50, 0, 64, 14, -56, -91, -65, -36, 51, -28}, expected: "YES"},
		testDescription{k: 6, a: []int{-58, -29, -35, -18, 43, -28, -76, 43, -13, 6}, expected: "NO"},
		testDescription{k: 1, a: []int{88, -17, -96, 43, 83, 99, 25, 90, -39, 86}, expected: "NO"},
	}
	testRunner(t, tests)
}

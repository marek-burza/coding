package qheap1

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
	"testing"
)

func generalizedTest(t *testing.T, which string, quick bool) {
	ioLines := make([][][]string, 2)
	for i, template := range []string{"input%s.txt", "output%s.txt"} {
		data, _ := os.ReadFile(filepath.Clean(fmt.Sprintf(template, which)))
		for line := range strings.SplitSeq(strings.TrimSpace(string(data)), "\n") {
			ioLines[i] = append(ioLines[i], strings.Fields(line))
		}
	}
	results := Run(quick, ioLines[0])
	if len(ioLines[1]) != len(results) {
		t.Errorf("Run - Expected %v results, got %v!", len(ioLines[1]), len(results))
		return
	}
	for i, expected := range ioLines[1] {
		if expected[0] != results[i] {
			t.Errorf("Run - Expected %v, got %v!", expected[0], results[i])
		}
	}
}

func TestSearchForAbsent(t *testing.T) {
	h := []int{6, 3, 0, 5}
	SheapBuild(h)
	if _, found := SheapSearch(h, -1); found {
		t.Errorf("SheapSearch - Expected nothing, got something!")
	}
}

func TestDeleteSwapsOnSameLevel(t *testing.T) {
	h := []int{0, 10, 8, 13, 14, 9}
	SheapDeleteIndex(&h, 4)
	if h[len(h)-1] == 9 {
		t.Errorf("SheapDeleteIndex - Expected anything but 9 at the end!")
	}
}

func TestExample(t *testing.T) {
	generalizedTest(t, "example", true)
	generalizedTest(t, "example", false)
}

func Test00(t *testing.T) {
	generalizedTest(t, "00", true)
	generalizedTest(t, "00", false)
}

func Test01(t *testing.T) {
	generalizedTest(t, "01", true)
	generalizedTest(t, "01", false)
}

func Test02(t *testing.T) {
	generalizedTest(t, "02", true)
	generalizedTest(t, "02", false)
}

func Test08(t *testing.T) {
	generalizedTest(t, "08", true)
}

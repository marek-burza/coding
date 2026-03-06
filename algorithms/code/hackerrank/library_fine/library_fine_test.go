package libraryfine

import (
	"fmt"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for i, template := range []string{"input%s.txt", "output%s.txt"} {
		data, _ := os.ReadFile(filepath.Clean(fmt.Sprintf(template, name)))
		for line := range strings.SplitSeq(strings.TrimSpace(string(data)), "\n") {
			ioLines[i] = append(ioLines[i], strings.Fields(line))
		}
	}
	d1, _ := strconv.Atoi(ioLines[0][0][0])
	m1, _ := strconv.Atoi(ioLines[0][0][1])
	y1, _ := strconv.Atoi(ioLines[0][0][2])
	d2, _ := strconv.Atoi(ioLines[0][1][0])
	m2, _ := strconv.Atoi(ioLines[0][1][1])
	y2, _ := strconv.Atoi(ioLines[0][1][2])
	result := LibraryFine(int32(d1), int32(m1), int32(y1), int32(d2), int32(m2), int32(y2))
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %d instead of %d!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

func Test01(t *testing.T) {
	Runner(t, "01")
}

func Test02(t *testing.T) {
	Runner(t, "02")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

func Test07(t *testing.T) {
	Runner(t, "07")
}

func Test09(t *testing.T) {
	Runner(t, "09")
}

func Test14(t *testing.T) {
	Runner(t, "14")
}

func Test15(t *testing.T) {
	Runner(t, "15")
}

func Test16(t *testing.T) {
	Runner(t, "16")
}

package maximizingxor

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
	l, _ := strconv.Atoi(ioLines[0][0][0])
	r, _ := strconv.Atoi(ioLines[0][1][0])
	result := MaximizingXor(int32(l), int32(r))
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %d instead of %d!", result, expected)
	}
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

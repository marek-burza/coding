package primsmstsub

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
	n, _ := strconv.Atoi(ioLines[0][0][0])
	m, _ := strconv.Atoi(ioLines[0][0][1])
	edges := make([][]int32, 0)
	for _, row := range ioLines[0][1 : 1+m] {
		edge := make([]int32, 0)
		for _, textual := range row {
			converted, _ := strconv.Atoi(textual)
			edge = append(edge, int32(converted))
		}
		edges = append(edges, edge)
	}
	start, _ := strconv.Atoi(ioLines[0][1+m][0])
	result := Prims(int32(n), edges, int32(start))
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test05(t *testing.T) {
	Runner(t, "05")
}

func Test06(t *testing.T) {
	Runner(t, "06")
}

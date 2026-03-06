package cutthetree

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
	data := make([]int32, 0)
	for _, textual := range ioLines[0][1] {
		converted, _ := strconv.Atoi(textual)
		data = append(data, int32(converted))
	}
	edges := make([][]int32, 0)
	for i := 2; i < len(ioLines[0]) && len(ioLines[0][i]) == 2; i++ {
		converted0, _ := strconv.Atoi(ioLines[0][i][0])
		converted1, _ := strconv.Atoi(ioLines[0][i][1])
		edge := []int32{int32(converted0), int32(converted1)}
		edges = append(edges, edge)
	}
	result := CutTheTree(data, edges)
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

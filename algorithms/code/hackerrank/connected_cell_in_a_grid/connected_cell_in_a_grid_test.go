package connectedcellinagrid

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
	matrix := make([][]int32, 0)
	for _, row := range ioLines[0][2:] {
		rowConverted := make([]int32, 0)
		for _, textual := range row {
			converted, _ := strconv.Atoi(textual)
			rowConverted = append(rowConverted, int32(converted))
		}
		matrix = append(matrix, rowConverted)
	}
	result := ConnectedCell(matrix)
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

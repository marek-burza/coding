package cavitymap

import (
	"fmt"
	"os"
	"path/filepath"
	"reflect"
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
	grid := make([]string, n)
	expected := make([]string, n)
	for i := range n {
		grid[i] = ioLines[0][i+1][0]
		expected[i] = ioLines[1][i][0]
	}
	result := CavityMap(grid)
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("CavityMap failed by returning wrong results - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

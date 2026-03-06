package bfsshortreach

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
	tests, _ := strconv.Atoi(ioLines[0][0][0])
	offset := 1
	for test := range tests {
		n, _ := strconv.Atoi(ioLines[0][offset][0])
		m, _ := strconv.Atoi(ioLines[0][offset][1])
		edges := make([][]int32, m)
		for i := 0; i < int(m); i++ {
			a, _ := strconv.Atoi(ioLines[0][offset+1+i][0])
			b, _ := strconv.Atoi(ioLines[0][offset+1+i][1])
			edges[i] = []int32{int32(a), int32(b)}
		}
		s, _ := strconv.Atoi(ioLines[0][offset+1+int(m)][0])
		offset += 1 + int(m) + 1
		result := Bfs(int32(n), int32(m), edges, int32(s))
		expected := make([]int32, len(ioLines[1][test]))
		for i, textual := range ioLines[1][test] {
			converted, _ := strconv.Atoi(textual)
			expected[i] = int32(converted)
		}
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

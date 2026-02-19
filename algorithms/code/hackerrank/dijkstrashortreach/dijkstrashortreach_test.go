// Package dijkstrashortreach implements https://www.hackerrank.com/challenges/dijkstrashortreach
package dijkstrashortreach

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
		path := fmt.Sprintf(template, name)
		cleanPath := filepath.Clean(path)
		data, _ := os.ReadFile(cleanPath)
		for line := range strings.SplitSeq(strings.TrimSpace(string(data)), "\n") {
			ioLines[i] = append(ioLines[i], strings.Fields(line))
		}
	}
	convertedTests, _ := strconv.Atoi(ioLines[0][0][0])
	tests := convertedTests
	offset := 1
	for test := range tests {
		convertedN, _ := strconv.ParseInt(ioLines[0][offset][0], 10, 32)
		n := int32(convertedN)
		convertedM, _ := strconv.Atoi(ioLines[0][offset][1])
		m := convertedM
		edges := make([][]int32, 0)
		for _, row := range ioLines[0][offset+1 : offset+1+m] {
			edge := make([]int32, 0)
			for _, textual := range row {
				converted, _ := strconv.ParseInt(textual, 10, 32)
				edge = append(edge, int32(converted))
			}
			edges = append(edges, edge)
		}
		convertedS, _ := strconv.ParseInt(ioLines[0][offset+1+m][0], 10, 32)
		s := int32(convertedS)
		offset += 1 + m + 1
		result := ShortestReach(n, edges, s)
		expected := make([]int32, 0)
		for _, textual := range ioLines[1][test] {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			expected = append(expected, int32(converted))
		}
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
		}
	}
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

func Test01(t *testing.T) {
	Runner(t, "01")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

func Test04(t *testing.T) {
	Runner(t, "04")
}

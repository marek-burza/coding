package dijkstrashortreach

import (
	"fmt"
	"os"
	"reflect"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for i, template := range []string{"input%s.txt", "output%s.txt"} {
		path := fmt.Sprintf(template, name)
		data, _ := os.ReadFile(path)
		for line := range strings.SplitSeq(strings.TrimSpace(string(data)), "\n") {
			ioLines[i] = append(ioLines[i], strings.Fields(line))
		}
	}
	convertedTests, _ := strconv.Atoi(ioLines[0][0][0])
	tests := int(convertedTests)
	offset := 1
	for test := range tests {
		convertedN, _ := strconv.Atoi(ioLines[0][offset][0])
		n := int32(convertedN)
		convertedM, _ := strconv.Atoi(ioLines[0][offset][1])
		m := int(convertedM)
		edges := make([][]int32, 0)
		for _, row := range ioLines[0][offset+1 : offset+1+m] {
			edge := make([]int32, 0)
			for _, textual := range row {
				converted, _ := strconv.Atoi(textual)
				edge = append(edge, int32(converted))
			}
			edges = append(edges, edge)
		}
		convertedS, _ := strconv.Atoi(ioLines[0][offset+1+m][0])
		s := int32(convertedS)
		offset += 1 + int(m) + 1
		result := ShortestReach(n, edges, s)
		expected := make([]int32, 0)
		for _, textual := range ioLines[1][test] {
			converted, _ := strconv.Atoi(textual)
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

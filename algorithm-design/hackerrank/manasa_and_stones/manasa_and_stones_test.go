package cavitymap

import (
	"bufio"
	"fmt"
	"os"
	"reflect"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	inPath := fmt.Sprintf("input%s.txt", name)
	input, fail := os.Open(inPath)
	if fail != nil {
		t.Fatalf("Failed opening input file: %s", fail)
	}
	inScanner := bufio.NewScanner(input)
	inScanner.Split(bufio.ScanLines)
	var inLines []string
	for inScanner.Scan() {
		inLines = append(inLines, inScanner.Text())
	}
	count, fail := strconv.Atoi(inLines[0])
	if fail != nil {
		t.Fatalf("Failed converting test count: %s", inLines[0])
	}
	results := make([][]int32, count)
	for i := 0; i < count; i++ {
		n, _ := strconv.ParseInt(inLines[1+i*3], 10, 32)
		a, _ := strconv.ParseInt(inLines[2+i*3], 10, 32)
		b, _ := strconv.ParseInt(inLines[3+i*3], 10, 32)
		results[i] = Stones(int32(n), int32(a), int32(b))
	}
	outPath := fmt.Sprintf("output%s.txt", name)
	output, fail := os.Open(outPath)
	if fail != nil {
		t.Fatalf("Failed opening output file: %s", fail)
	}
	outScanner := bufio.NewScanner(output)
	outScanner.Split(bufio.ScanLines)
	var outLines []string
	for outScanner.Scan() {
		outLines = append(outLines, outScanner.Text())
	}
	if len(results) != len(outLines) {
		t.Errorf("Failed by returning wrong number of results - %d instead of %d!", len(results), len(outLines))
	}
	for i := 0; i < len(outLines); i++ {
		expected := make([]int32, 0)
		for _, value := range strings.Split(outLines[i], " ") {
			converted, _ := strconv.ParseInt(value, 10, 32)
			expected = append(expected, int32(converted))
		}
		if !reflect.DeepEqual(results[i], expected) {
			t.Errorf("Failed in line %d of test %s!", i, name)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

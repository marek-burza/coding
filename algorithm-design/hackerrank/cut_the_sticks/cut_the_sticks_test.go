package cavitymap

import (
	"bufio"
	"fmt"
	"os"
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
	arr := make([]int32, count)
	for i, value := range strings.Split(inLines[1], " ") {
		converted, _ := strconv.ParseInt(value, 10, 32)
		arr[i] = int32(converted)
	}
	result := CutTheSticks(arr)
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
	if len(result) != len(outLines) {
		t.Errorf("Failed by returning wrong number of results - %d instead of %d!", len(result), len(outLines))
	}
	for i := 0; i < len(outLines); i++ {
		expected, _ := strconv.ParseInt(outLines[i], 10, 32)
		if result[i] != int32(expected) {
			t.Errorf("Failed in line %d of test %s!", i, name)
		}
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

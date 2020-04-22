package countluck

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
		inLines = append(inLines, strings.TrimSpace(inScanner.Text()))
	}
	tests, _ := strconv.ParseInt(inLines[0], 10, 32)
	results := make([]string, tests)
	offset := 1
	for test := 0; test < int(tests); test++ {
		items := strings.Split(inLines[offset], " ")
		convertedN, _ := strconv.ParseInt(items[0], 10, 32)
		n := int(convertedN)
		matrix := inLines[offset+1 : offset+1+n+1]
		convertedK, _ := strconv.ParseInt(inLines[offset+1+n], 10, 32)
		k := int32(convertedK)
		results[test] = CountLuck(matrix, k)
		offset += 2 + n
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
		outLines = append(outLines, strings.TrimSpace(outScanner.Text()))
	}
	expected := outLines
	if !reflect.DeepEqual(results, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", results, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func TestEmpy(t *testing.T) {
	result := CountLuck([]string{}, 0)
	expected := "Oops!"
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

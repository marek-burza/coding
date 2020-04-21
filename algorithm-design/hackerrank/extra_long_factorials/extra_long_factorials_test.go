package extralongfactorials

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
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
	n, _ := strconv.ParseInt(inLines[0], 10, 32)
	result := ExtraLongFactorials(int32(n))
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
	expected := outLines[0]
	if result != expected {
		t.Errorf("Failed by returning wrong value - %s instead of %s!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

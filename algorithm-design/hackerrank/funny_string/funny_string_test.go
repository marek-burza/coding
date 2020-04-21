package funnystring

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
	count, _ := strconv.ParseInt(inLines[0], 10, 32)
	results := make([]string, count)
	for i := 0; int64(i) < count; i++ {
		s := inLines[1+i]
		results[i] = FunnyString(s)
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
	for i := 0; int64(i) < count; i++ {
		expected := outLines[i]
		if results[i] != expected {
			t.Errorf("Failed by returning wrong value - %s instead of %s!", results[i], expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

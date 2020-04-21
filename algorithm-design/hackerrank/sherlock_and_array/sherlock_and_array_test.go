package sherlockandarray

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
	results := make([]string, count)
	for i := 0; i < count; i++ {
		converted, _ := strconv.ParseInt(inLines[1+i*2], 10, 32)
		arr := make([]int32, converted)
		for j, value := range strings.Split(inLines[2+i*2], " ") {
			converted, _ := strconv.ParseInt(value, 10, 32)
			arr[j] = int32(converted)
		}
		results[i] = BalancedSums(arr)
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
		expected := outLines[i]
		if results[i] != expected {
			t.Errorf("Failed in line %d of test %s!", i, name)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

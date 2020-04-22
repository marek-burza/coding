package thegridsearch

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
	defer input.Close()
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
		itemsG := strings.Split(inLines[offset], " ")
		convertedG, _ := strconv.ParseInt(itemsG[0], 10, 32)
		rG := int(convertedG)
		g := inLines[offset+1 : offset+1+rG]
		itemsP := strings.Split(inLines[offset+1+rG], " ")
		convertedP, _ := strconv.ParseInt(itemsP[0], 10, 32)
		rP := int(convertedP)
		p := inLines[offset+1+rG+1 : offset+1+rG+1+rP]
		offset += rG + rP + 2
		results[test] = GridSearch(g, p)
	}
	outPath := fmt.Sprintf("output%s.txt", name)
	output, fail := os.Open(outPath)
	defer output.Close()
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

func Test05(t *testing.T) {
	Runner(t, "05")
}

func Test07(t *testing.T) {
	Runner(t, "07")
}

func Test08(t *testing.T) {
	Runner(t, "08")
}

func Test09(t *testing.T) {
	Runner(t, "09")
}

func Test15(t *testing.T) {
	Runner(t, "15")
}

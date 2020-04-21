package jumpingontheclouds

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
	count, _ := strconv.ParseInt(inLines[0], 10, 32)
	c := make([]int32, count)
	for i, textual := range strings.Split(inLines[1], " ") {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		c[i] = int32(converted)
	}
	result := JumpingOnClouds(c)
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
	textual := outLines[0]
	converted, _ := strconv.ParseInt(textual, 10, 32)
	expected := int32(converted)
	if result != expected {
		t.Errorf("Failed by returning wrong value - %d instead of %d!", result, expected)
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

func TestMissingExample(t *testing.T) {
	expected := int32(1)
	result := JumpingOnClouds([]int32{0, 0})
	if result != expected {
		t.Errorf("Failed by returning wrong value - %d instead of %d!", result, expected)
	}
}

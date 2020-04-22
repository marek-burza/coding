package pairs

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
	items := strings.Split(inLines[0], " ")
	count, _ := strconv.ParseInt(items[0], 10, 32)
	arr := make([]int32, count)
	for i, textual := range strings.Split(inLines[1], " ") {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		arr[i] = int32(converted)
	}
	k, _ := strconv.ParseInt(items[1], 10, 32)
	result := Pairs(int32(k), arr)
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
	converted, _ := strconv.ParseInt(outLines[0], 10, 32)
	expected := int32(converted)
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func TestOther(t *testing.T) {
	expected := int32(4)
	result := Pairs(1, []int32{1, 5, 3, 4, 2})
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

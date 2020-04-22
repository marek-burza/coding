package maximumperimetertriangle

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
	count, _ := strconv.ParseInt(inLines[0], 10, 32)
	sticks := make([]int32, count)
	for i, textual := range strings.Split(inLines[1], " ") {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		sticks[i] = int32(converted)
	}
	result := MaximumPerimeterTriangle(sticks)
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
	expected := make([]int32, 0)
	for _, textual := range strings.Split(outLines[0], " ") {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		expected = append(expected, int32(converted))
	}
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test02(t *testing.T) {
	Runner(t, "02")
}

func TestDegenerate(t *testing.T) {
	expected := []int32{-1}
	result := MaximumPerimeterTriangle([]int32{})
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestACB(t *testing.T) {
	expected := []int32{-1}
	result := MaximumPerimeterTriangle([]int32{0, 1, 1})
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

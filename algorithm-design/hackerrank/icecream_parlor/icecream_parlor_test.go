package icecreamparlor

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
	results := make([][]int32, count)
	for i := 0; i < int(count); i++ {
		converted, _ := strconv.ParseInt(inLines[1+i*3], 10, 32)
		m := int32(converted)
		arr := make([]int32, 0)
		for _, textual := range strings.Split(inLines[3+i*3], " ") {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			arr = append(arr, int32(converted))
		}
		results[i] = IcecreamParlor(m, arr)
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
	for i := 0; i < int(count); i++ {
		expected := make([]int32, 0)
		for _, textual := range strings.Split(outLines[i], " ") {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			expected = append(expected, int32(converted))
		}
		if !reflect.DeepEqual(expected, results[i]) {
			t.Errorf("Failed by returning wrong value - %v instead of %v!", results[i], expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test02(t *testing.T) {
	Runner(t, "02")
}

func TestSame(t *testing.T) {
	expected := []int32{3, 4}
	result := IcecreamParlor(6, []int32{3, 1, 2, 4})
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestNone(t *testing.T) {
	expected := []int32{0, 0}
	result := IcecreamParlor(10, []int32{3, 1, 2, 4})
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

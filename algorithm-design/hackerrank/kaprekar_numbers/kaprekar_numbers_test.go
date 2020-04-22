package kaprekarnumbers

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
	p, _ := strconv.ParseInt(inLines[0], 10, 32)
	q, _ := strconv.ParseInt(inLines[1], 10, 32)
	result := KaprekarNumbers(int32(p), int32(q))
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
	expected := strings.Split(outLines[0], " ")
	for i := 0; i < len(expected); i++ {
		expected[i] = strings.TrimSpace(expected[i])
	}
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
		fmt.Printf("%v\n", []rune(expected[0]))
		fmt.Printf("%v\n", []rune(result[0]))
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test06(t *testing.T) {
	Runner(t, "06")
}

func Test6(t *testing.T) {
	Runner(t, "6")
}

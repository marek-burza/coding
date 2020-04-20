package cavitymap

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
	h, _ := strconv.ParseInt(inLines[0], 10, 32)
	m, _ := strconv.ParseInt(inLines[1], 10, 32)
	result := TimeInWords(int32(h), int32(m))
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
	if result != outLines[0] {
		t.Errorf("Failed by returning wrong value - '%s' instead of '%s'!", result, outLines[0])
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

func TestExample2(t *testing.T) {
	Runner(t, "_example_2")
}

func Test0359(t *testing.T) {
	result := TimeInWords(3, 59)
	if "one minute to four" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'one minute to four'!", result)
	}
}

func Test0301(t *testing.T) {
	result := TimeInWords(3, 1)
	if "one minute past three" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'one minute past three'!", result)
	}
}

func Test0345(t *testing.T) {
	result := TimeInWords(3, 45)
	if "quarter to four" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'quarter to four'!", result)
	}
}

func Test0330(t *testing.T) {
	result := TimeInWords(3, 30)
	if "half past three" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'half past three'!", result)
	}
}

func Test0320(t *testing.T) {
	result := TimeInWords(3, 20)
	if "twenty minutes past three" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'twenty minutes past three'!", result)
	}
}

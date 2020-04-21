package cavitymap

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
	line1 := strings.Split(inLines[0], " ")
	line2 := strings.Split(inLines[1], " ")
	d1, _ := strconv.ParseInt(line1[0], 10, 32)
	m1, _ := strconv.ParseInt(line1[1], 10, 32)
	y1, _ := strconv.ParseInt(line1[2], 10, 32)
	d2, _ := strconv.ParseInt(line2[0], 10, 32)
	m2, _ := strconv.ParseInt(line2[1], 10, 32)
	y2, _ := strconv.ParseInt(line2[2], 10, 32)
	result := LibraryFine(int32(d1), int32(m1), int32(y1), int32(d2), int32(m2), int32(y2))
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
	expected, _ := strconv.ParseInt(outLines[0], 10, 32)
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %d instead of %d!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

func Test01(t *testing.T) {
	Runner(t, "01")
}

func Test02(t *testing.T) {
	Runner(t, "02")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

func Test14(t *testing.T) {
	Runner(t, "14")
}

func Test15(t *testing.T) {
	Runner(t, "15")
}

func Test16(t *testing.T) {
	Runner(t, "16")
}

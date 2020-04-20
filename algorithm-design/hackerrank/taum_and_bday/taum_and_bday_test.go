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
	count, fail := strconv.Atoi(inLines[0])
	if fail != nil {
		t.Fatalf("Failed converting test count: %s", inLines[0])
	}
	results := make([]int64, count)
	for index := 0; index < count; index++ {
		line1 := strings.Split(inLines[1+index*2], " ")
		b, _ := strconv.Atoi(line1[0])
		w, _ := strconv.Atoi(line1[1])
		line2 := strings.Split(inLines[2+index*2], " ")
		bc, _ := strconv.Atoi(line2[0])
		wc, _ := strconv.Atoi(line2[1])
		z, _ := strconv.Atoi(line2[2])
		results[index] = TaumBday(int32(b), int32(w), int32(bc), int32(wc), int32(z))
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
		t.Errorf("TaumBday failed by returning wrong number of results - %d instead of %d!", len(results), len(outLines))
	}
	for i := 0; i < len(outLines); i++ {
		expected, _ := strconv.ParseInt(outLines[i], 10, 64)
		if results[i] != expected {
			t.Errorf("TaumBday failed in line %d of test %s!", i, name)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test05(t *testing.T) {
	Runner(t, "05")
}

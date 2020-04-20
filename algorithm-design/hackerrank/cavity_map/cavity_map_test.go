package cavitymap

import (
	"bufio"
	"fmt"
	"os"
	"testing"
)

func Runner(t *testing.T, name string) {
	inPath := fmt.Sprintf("input_%s.txt", name)
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
	result := CavityMap(inLines[1:])
	outPath := fmt.Sprintf("output_%s.txt", name)
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
	for i := 0; i < len(outLines); i++ {
		if result[i] != outLines[i] {
			t.Errorf("CavityMap failed in line %d of test %s!", i, name)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "example")
}

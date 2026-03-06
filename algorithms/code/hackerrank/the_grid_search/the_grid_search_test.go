package thegridsearch

import (
	"fmt"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for i, template := range []string{"input%s.txt", "output%s.txt"} {
		data, _ := os.ReadFile(filepath.Clean(fmt.Sprintf(template, name)))
		for line := range strings.SplitSeq(strings.TrimSpace(string(data)), "\n") {
			ioLines[i] = append(ioLines[i], strings.Fields(line))
		}
	}
	tests, _ := strconv.Atoi(ioLines[0][0][0])
	offset := 1
	for test := range tests {
		convertedG, _ := strconv.Atoi(ioLines[0][offset][0])
		rG := int(convertedG)
		g := make([]string, 0)
		for _, row := range ioLines[0][offset+1 : offset+1+rG] {
			g = append(g, row[0])
		}
		convertedP, _ := strconv.Atoi(ioLines[0][offset+1+rG][0])
		rP := int(convertedP)
		p := make([]string, 0)
		for _, row := range ioLines[0][offset+1+rG+1 : offset+1+rG+1+rP] {
			p = append(p, row[0])
		}
		offset += rG + rP + 2
		result := GridSearch(g, p)
		expected := ioLines[1][test][0]
		if result != expected {
			t.Errorf("Failed by returning wrong value for test %d - %v instead of %v!", test, result, expected)
		}
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

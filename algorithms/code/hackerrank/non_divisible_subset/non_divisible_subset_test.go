package nondivisiblesubset

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for index, template := range []string{"input%s.txt", "output%s.txt"} {
		path := fmt.Sprintf(template, name)
		cleanPath := filepath.Clean(path)
		file, fail := os.Open(cleanPath)
		if fail != nil {
			t.Fatalf("Failed opening file %s: %s", path, fail)
		}
		defer file.Close() //nolint:errcheck,gosec
		lines := make([][]string, 0)
		ioLines[index] = lines
		reader := bufio.NewReader(file)
		for {
			var buffer bytes.Buffer
			var raw []byte
			var prefix bool
			for {
				raw, prefix, fail = reader.ReadLine()
				buffer.Write(raw)
				if !prefix || fail != nil {
					break
				}
			}
			ioLines[index] = append(ioLines[index], strings.Split(strings.TrimSpace(buffer.String()), " "))
			if fail == io.EOF {
				break
			} else if fail != nil {
				t.Fatalf("Failed reading file %s: %s", path, fail)
			}
		}
	}
	convertedN, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	n := int32(convertedN)
	convertedK, _ := strconv.ParseInt(ioLines[0][0][1], 10, 32)
	k := int32(convertedK)
	s := make([]int32, n)
	for i, textual := range ioLines[0][1] {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		s[i] = int32(converted)
	}
	result := NonDivisibleSubset(k, s)
	convertedExpected, _ := strconv.ParseInt(ioLines[1][0][0], 10, 32)
	expected := int32(convertedExpected)
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test06(t *testing.T) {
	Runner(t, "06")
}

func Test07(t *testing.T) {
	Runner(t, "07")
}

func Test16(t *testing.T) {
	Runner(t, "16")
}

package thegridsearch

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for index, template := range []string{"input%s.txt", "output%s.txt"} {
		path := fmt.Sprintf(template, name)
		file, fail := os.Open(path)
		if fail != nil {
			t.Fatalf("Failed opening file %s: %s", path, fail)
		}
		defer file.Close()
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
	tests, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	offset := 1
	for test := 0; test < int(tests); test++ {
		convertedG, _ := strconv.ParseInt(ioLines[0][offset][0], 10, 32)
		rG := int(convertedG)
		g := make([]string, 0)
		for _, row := range ioLines[0][offset+1 : offset+1+rG] {
			g = append(g, row[0])
		}
		convertedP, _ := strconv.ParseInt(ioLines[0][offset+1+rG][0], 10, 32)
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

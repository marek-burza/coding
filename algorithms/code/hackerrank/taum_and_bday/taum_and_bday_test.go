package taumandbday

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
	count, _ := strconv.Atoi(ioLines[0][0][0])
	for index := range count {
		line1 := ioLines[0][1+index*2]
		b, _ := strconv.Atoi(line1[0])
		w, _ := strconv.Atoi(line1[1])
		line2 := ioLines[0][2+index*2]
		bc, _ := strconv.Atoi(line2[0])
		wc, _ := strconv.Atoi(line2[1])
		z, _ := strconv.Atoi(line2[2])
		result := TaumBday(int32(b), int32(w), int32(bc), int32(wc), int32(z))
		expected, _ := strconv.ParseInt(ioLines[1][index][0], 10, 64)
		if result != expected {
			t.Errorf("Failed by returning wrong value for test %d - %d instead of %d!", index, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test05(t *testing.T) {
	Runner(t, "05")
}

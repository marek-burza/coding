package libraryfine

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
	d1, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	m1, _ := strconv.ParseInt(ioLines[0][0][1], 10, 32)
	y1, _ := strconv.ParseInt(ioLines[0][0][2], 10, 32)
	d2, _ := strconv.ParseInt(ioLines[0][1][0], 10, 32)
	m2, _ := strconv.ParseInt(ioLines[0][1][1], 10, 32)
	y2, _ := strconv.ParseInt(ioLines[0][1][2], 10, 32)
	result := LibraryFine(int32(d1), int32(m1), int32(y1), int32(d2), int32(m2), int32(y2))
	expected, _ := strconv.ParseInt(ioLines[1][0][0], 10, 32)
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

func Test07(t *testing.T) {
	Runner(t, "07")
}

func Test09(t *testing.T) {
	Runner(t, "09")
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

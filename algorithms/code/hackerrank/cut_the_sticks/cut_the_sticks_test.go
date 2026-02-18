package cutthesticks

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"reflect"
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
	arr := make([]int32, count)
	for i, value := range ioLines[0][1] {
		converted, _ := strconv.ParseInt(value, 10, 32)
		arr[i] = int32(converted)
	}
	result := CutTheSticks(arr)
	expected := make([]int32, 0)
	for _, line := range ioLines[1] {
		converted, _ := strconv.ParseInt(line[0], 10, 32)
		expected = append(expected, int32(converted))
	}
	if reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

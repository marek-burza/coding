package countluck

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"os"
	"reflect"
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
		convertedN, _ := strconv.ParseInt(ioLines[0][offset][0], 10, 32)
		n := int(convertedN)
		matrix := make([]string, 0)
		for _, line := range ioLines[0][offset+1 : offset+1+n+1] {
			matrix = append(matrix, line[0])
		}
		convertedK, _ := strconv.ParseInt(ioLines[0][offset+1+n][0], 10, 32)
		k := int32(convertedK)
		offset += 2 + n
		result := CountLuck(matrix, k)
		expected := ioLines[1][test][0]
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value for test %d - %v instead of %v!", test, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func TestEmpy(t *testing.T) {
	result := CountLuck([]string{}, 0)
	expected := "Oops!"
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

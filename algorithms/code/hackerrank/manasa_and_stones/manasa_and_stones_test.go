package cavitymap

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
	count, _ := strconv.Atoi(ioLines[0][0][0])
	for i := range count {
		n, _ := strconv.ParseInt(ioLines[0][1+i*3][0], 10, 32)
		a, _ := strconv.ParseInt(ioLines[0][2+i*3][0], 10, 32)
		b, _ := strconv.ParseInt(ioLines[0][3+i*3][0], 10, 32)
		result := Stones(int32(n), int32(a), int32(b))
		expected := make([]int32, 0)
		for _, value := range ioLines[1][i] {
			converted, _ := strconv.ParseInt(value, 10, 32)
			expected = append(expected, int32(converted))
		}
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value for test %d - %d instead of %d!", i, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

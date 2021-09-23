package icecreamparlor

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
	count, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	for i := 0; i < int(count); i++ {
		converted, _ := strconv.ParseInt(ioLines[0][1+i*3][0], 10, 32)
		m := int32(converted)
		arr := make([]int32, 0)
		for _, textual := range ioLines[0][3+i*3] {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			arr = append(arr, int32(converted))
		}
		result := IcecreamParlor(m, arr)
		expected := make([]int32, 0)
		for _, textual := range ioLines[1][i] {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			expected = append(expected, int32(converted))
		}
		if !reflect.DeepEqual(expected, result) {
			t.Errorf("Failed by returning wrong value for test %d - %v instead of %v!", i, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test02(t *testing.T) {
	Runner(t, "02")
}

func TestSame(t *testing.T) {
	expected := []int32{3, 4}
	result := IcecreamParlor(6, []int32{3, 1, 2, 4})
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestNone(t *testing.T) {
	expected := []int32{0, 0}
	result := IcecreamParlor(10, []int32{3, 1, 2, 4})
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

package pairs

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
	count, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	arr := make([]int32, count)
	for i, textual := range ioLines[0][1] {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		arr[i] = int32(converted)
	}
	k, _ := strconv.ParseInt(ioLines[0][0][1], 10, 32)
	result := Pairs(int32(k), arr)
	converted, _ := strconv.ParseInt(ioLines[1][0][0], 10, 32)
	expected := int32(converted)
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func TestOther(t *testing.T) {
	expected := int32(4)
	result := Pairs(1, []int32{1, 5, 3, 4, 2})
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

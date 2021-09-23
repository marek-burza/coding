package sherlockandarray

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
	count, _ := strconv.Atoi(ioLines[0][0][0])
	for i := 0; i < count; i++ {
		converted, _ := strconv.ParseInt(ioLines[0][1+i*2][0], 10, 32)
		arr := make([]int32, converted)
		for j, value := range ioLines[0][2+i*2] {
			converted, _ := strconv.ParseInt(value, 10, 32)
			arr[j] = int32(converted)
		}
		result := BalancedSums(arr)
		expected := ioLines[1][i][0]
		if result != expected {
			t.Errorf("Failed by returning wrong value for test %d - %s instead of %s!", i, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

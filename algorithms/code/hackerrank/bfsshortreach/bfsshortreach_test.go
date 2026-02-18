package bfsshortreach

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
	convertedTests, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	tests := int(convertedTests)
	offset := 1
	for test := range tests {
		convertedN, _ := strconv.ParseInt(ioLines[0][offset][0], 10, 32)
		n := int32(convertedN)
		convertedM, _ := strconv.ParseInt(ioLines[0][offset][1], 10, 32)
		m := int32(convertedM)
		edges := make([][]int32, m)
		for i := 0; i < int(m); i++ {
			convertedA, _ := strconv.ParseInt(ioLines[0][offset+1+i][0], 10, 32)
			convertedB, _ := strconv.ParseInt(ioLines[0][offset+1+i][1], 10, 32)
			edges[i] = []int32{int32(convertedA), int32(convertedB)}
		}
		convertedS, _ := strconv.ParseInt(ioLines[0][offset+1+int(m)][0], 10, 32)
		s := int32(convertedS)
		offset += 1 + int(m) + 1
		result := Bfs(n, m, edges, s)
		expected := make([]int32, len(ioLines[1][test]))
		for i, textual := range ioLines[1][test] {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			expected[i] = int32(converted)
		}
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

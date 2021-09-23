package cutthetree

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
	data := make([]int32, 0)
	for _, textual := range ioLines[0][1] {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		data = append(data, int32(converted))
	}
	edges := make([][]int32, 0)
	for i := 2; i < len(ioLines[0]) && len(ioLines[0][i]) == 2; i++ {
		converted0, _ := strconv.ParseInt(ioLines[0][i][0], 10, 32)
		converted1, _ := strconv.ParseInt(ioLines[0][i][1], 10, 32)
		edge := []int32{int32(converted0), int32(converted1)}
		edges = append(edges, edge)
	}
	result := CutTheTree(data, edges)
	convertedExpected, _ := strconv.ParseInt(ioLines[1][0][0], 10, 32)
	expected := int32(convertedExpected)
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

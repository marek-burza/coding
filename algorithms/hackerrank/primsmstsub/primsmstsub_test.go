package primsmstsub

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
	convertedN, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	n := int32(convertedN)
	convertedM, _ := strconv.ParseInt(ioLines[0][0][1], 10, 32)
	m := int32(convertedM)
	edges := make([][]int32, 0)
	for _, row := range ioLines[0][1 : 1+m] {
		edge := make([]int32, 0)
		for _, textual := range row {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			edge = append(edge, int32(converted))
		}
		edges = append(edges, edge)
	}
	convertedStart, _ := strconv.ParseInt(ioLines[0][1+m][0], 10, 32)
	start := int32(convertedStart)
	result := Prims(n, edges, start)
	convertedExpected, _ := strconv.ParseInt(ioLines[1][0][0], 10, 32)
	expected := int32(convertedExpected)
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test05(t *testing.T) {
	Runner(t, "05")
}

func Test06(t *testing.T) {
	Runner(t, "06")
}

func compareInt(thisRaw interface{}, otherRaw interface{}) int {
	this := thisRaw.(*int)
	other := otherRaw.(*int)
	return *this - *other
}

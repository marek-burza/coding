package dijkstrashortreach

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
	for test := 0; test < tests; test++ {
		convertedN, _ := strconv.ParseInt(ioLines[0][offset][0], 10, 32)
		n := int32(convertedN)
		convertedM, _ := strconv.ParseInt(ioLines[0][offset][1], 10, 32)
		m := int(convertedM)
		edges := make([][]int32, 0)
		for _, row := range ioLines[0][offset+1 : offset+1+m] {
			edge := make([]int32, 0)
			for _, textual := range row {
				converted, _ := strconv.ParseInt(textual, 10, 32)
				edge = append(edge, int32(converted))
			}
			edges = append(edges, edge)
		}
		convertedS, _ := strconv.ParseInt(ioLines[0][offset+1+m][0], 10, 32)
		s := int32(convertedS)
		offset += 1 + int(m) + 1
		result := ShortestReach(n, edges, s)
		expected := make([]int32, 0)
		for _, textual := range ioLines[1][test] {
			converted, _ := strconv.ParseInt(textual, 10, 32)
			expected = append(expected, int32(converted))
		}
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
		}
	}
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

func Test01(t *testing.T) {
	Runner(t, "01")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

func Test04(t *testing.T) {
	Runner(t, "04")
}

func compareInt(thisRaw interface{}, otherRaw interface{}) int {
	this := thisRaw.(*int)
	other := otherRaw.(*int)
	return *this - *other
}

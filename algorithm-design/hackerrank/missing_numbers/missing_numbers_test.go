package missingnumbers

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
	inPath := fmt.Sprintf("input%s.txt", name)
	input, fail := os.Open(inPath)
	defer input.Close()
	if fail != nil {
		t.Fatalf("Failed opening input file: %s", fail)
	}
	var inLines []string
	reader := bufio.NewReader(input)
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
		if fail == io.EOF {
			break
		}
		inLines = append(inLines, buffer.String())
	}
	if fail != io.EOF {
		t.Fatalf("Failed reading input file: %s", fail)
	}
	arr := make([]int32, 0)
	for _, textual := range strings.Split(inLines[1], " ") {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		arr = append(arr, int32(converted))
	}
	fmt.Println(arr)
	brr := make([]int32, 0)
	for _, textual := range strings.Split(inLines[3], " ") {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		brr = append(brr, int32(converted))
	}
	result := MissingNumbers(arr, brr)
	outPath := fmt.Sprintf("output%s.txt", name)
	output, fail := os.Open(outPath)
	defer output.Close()
	if fail != nil {
		t.Fatalf("Failed opening output file: %s", fail)
	}
	outScanner := bufio.NewScanner(output)
	outScanner.Split(bufio.ScanLines)
	var outLines []string
	for outScanner.Scan() {
		outLines = append(outLines, strings.TrimSpace(outScanner.Text()))
	}
	expected := make([]int32, 0)
	for _, textual := range strings.Split(outLines[0], " ") {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		expected = append(expected, int32(converted))
	}
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test01(t *testing.T) {
	Runner(t, "01")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

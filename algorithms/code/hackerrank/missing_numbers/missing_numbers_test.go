package missingnumbers

import (
	"fmt"
	"os"
	"path/filepath"
	"reflect"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for i, template := range []string{"input%s.txt", "output%s.txt"} {
		data, _ := os.ReadFile(filepath.Clean(fmt.Sprintf(template, name)))
		for line := range strings.SplitSeq(strings.TrimSpace(string(data)), "\n") {
			ioLines[i] = append(ioLines[i], strings.Fields(line))
		}
	}
	arr := make([]int32, 0)
	for _, textual := range ioLines[0][1] {
		converted, _ := strconv.Atoi(textual)
		arr = append(arr, int32(converted))
	}
	brr := make([]int32, 0)
	for _, textual := range ioLines[0][3] {
		converted, _ := strconv.Atoi(textual)
		brr = append(brr, int32(converted))
	}
	result := MissingNumbers(arr, brr)
	expected := make([]int32, 0)
	for _, textual := range ioLines[1][0] {
		converted, _ := strconv.Atoi(textual)
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

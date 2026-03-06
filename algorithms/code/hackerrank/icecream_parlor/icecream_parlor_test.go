package icecreamparlor

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
	count, _ := strconv.Atoi(ioLines[0][0][0])
	for i := range count {
		m, _ := strconv.Atoi(ioLines[0][1+i*3][0])
		arr := make([]int32, 0)
		for _, textual := range ioLines[0][3+i*3] {
			converted, _ := strconv.Atoi(textual)
			arr = append(arr, int32(converted))
		}
		result := IcecreamParlor(int32(m), arr)
		expected := make([]int32, 0)
		for _, textual := range ioLines[1][i] {
			converted, _ := strconv.Atoi(textual)
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

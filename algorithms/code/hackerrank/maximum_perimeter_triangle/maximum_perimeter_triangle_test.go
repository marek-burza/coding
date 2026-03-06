package maximumperimetertriangle

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
	sticks := make([]int32, count)
	for i, textual := range ioLines[0][1] {
		converted, _ := strconv.Atoi(textual)
		sticks[i] = int32(converted)
	}
	result := MaximumPerimeterTriangle(sticks)
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

func Test02(t *testing.T) {
	Runner(t, "02")
}

func TestDegenerate(t *testing.T) {
	expected := []int32{-1}
	result := MaximumPerimeterTriangle([]int32{})
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestACB(t *testing.T) {
	expected := []int32{-1}
	result := MaximumPerimeterTriangle([]int32{0, 1, 1})
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

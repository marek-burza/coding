package pairs

import (
	"fmt"
	"os"
	"path/filepath"
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
	arr := make([]int32, count)
	for i, textual := range ioLines[0][1] {
		converted, _ := strconv.Atoi(textual)
		arr[i] = int32(converted)
	}
	k, _ := strconv.Atoi(ioLines[0][0][1])
	result := Pairs(int32(k), arr)
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
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

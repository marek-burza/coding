package divisiblesumpairs

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
	n, _ := strconv.Atoi(ioLines[0][0][0])
	k, _ := strconv.Atoi(ioLines[0][0][1])
	arr := make([]int32, n)
	for i, textual := range ioLines[0][1] {
		converted, _ := strconv.Atoi(textual)
		arr[i] = int32(converted)
	}
	result := DivisibleSumPairs(int32(n), int32(k), arr)
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test02(t *testing.T) {
	Runner(t, "02")
}

func Test06(t *testing.T) {
	Runner(t, "06")
}

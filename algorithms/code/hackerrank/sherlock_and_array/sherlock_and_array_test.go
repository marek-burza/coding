package sherlockandarray

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
	for i := range count {
		converted, _ := strconv.Atoi(ioLines[0][1+i*2][0])
		arr := make([]int32, converted)
		for j, value := range ioLines[0][2+i*2] {
			converted, _ := strconv.Atoi(value)
			arr[j] = int32(converted)
		}
		result := BalancedSums(arr)
		expected := ioLines[1][i][0]
		if result != expected {
			t.Errorf("Failed by returning wrong value for test %d - %s instead of %s!", i, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

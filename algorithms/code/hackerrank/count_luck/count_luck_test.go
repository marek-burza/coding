package countluck

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
	tests, _ := strconv.Atoi(ioLines[0][0][0])
	offset := 1
	for test := range tests {
		n, _ := strconv.Atoi(ioLines[0][offset][0])
		matrix := make([]string, 0)
		for _, line := range ioLines[0][offset+1 : offset+1+n+1] {
			matrix = append(matrix, line[0])
		}
		k, _ := strconv.Atoi(ioLines[0][offset+1+n][0])
		offset += 2 + n
		result := CountLuck(matrix, int32(k))
		expected := ioLines[1][test][0]
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value for test %d - %v instead of %v!", test, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func TestEmpy(t *testing.T) {
	result := CountLuck([]string{}, 0)
	expected := "Oops!"
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

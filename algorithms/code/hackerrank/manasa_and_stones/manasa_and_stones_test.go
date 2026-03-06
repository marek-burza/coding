package manasaandstones

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
		n, _ := strconv.Atoi(ioLines[0][1+i*3][0])
		a, _ := strconv.Atoi(ioLines[0][2+i*3][0])
		b, _ := strconv.Atoi(ioLines[0][3+i*3][0])
		result := Stones(int32(n), int32(a), int32(b))
		expected := make([]int32, 0)
		for _, value := range ioLines[1][i] {
			converted, _ := strconv.Atoi(value)
			expected = append(expected, int32(converted))
		}
		if !reflect.DeepEqual(result, expected) {
			t.Errorf("Failed by returning wrong value for test %d - %d instead of %d!", i, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

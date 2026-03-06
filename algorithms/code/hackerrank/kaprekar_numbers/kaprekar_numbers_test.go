package kaprekarnumbers

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
	p, _ := strconv.Atoi(ioLines[0][0][0])
	q, _ := strconv.Atoi(ioLines[0][1][0])
	result := KaprekarNumbers(int32(p), int32(q))
	expected := ioLines[1][0]
	if !reflect.DeepEqual(result, expected) {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test06(t *testing.T) {
	Runner(t, "06")
}

func Test6(t *testing.T) {
	Runner(t, "6")
}

package funnystring

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
		s := ioLines[0][1+i][0]
		result := FunnyString(s)
		expected := strings.Join(ioLines[1][i], " ")
		if result != expected {
			t.Errorf("Failed by returning wrong value for test %d - %s instead of %s!", i, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

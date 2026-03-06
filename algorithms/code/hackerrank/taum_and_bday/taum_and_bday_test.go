package taumandbday

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
	for index := range count {
		line1 := ioLines[0][1+index*2]
		b, _ := strconv.Atoi(line1[0])
		w, _ := strconv.Atoi(line1[1])
		line2 := ioLines[0][2+index*2]
		bc, _ := strconv.Atoi(line2[0])
		wc, _ := strconv.Atoi(line2[1])
		z, _ := strconv.Atoi(line2[2])
		result := TaumBday(int32(b), int32(w), int32(bc), int32(wc), int32(z))
		expected, _ := strconv.ParseInt(ioLines[1][index][0], 10, 64)
		if result != expected {
			t.Errorf("Failed by returning wrong value for test %d - %d instead of %d!", index, result, expected)
		}
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

func Test05(t *testing.T) {
	Runner(t, "05")
}

package jumpingontheclouds

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
	c := make([]int32, count)
	for i, textual := range ioLines[0][1] {
		converted, _ := strconv.Atoi(textual)
		c[i] = int32(converted)
	}
	result := JumpingOnClouds(c)
	expected, _ := strconv.Atoi(ioLines[1][0][0])
	if result != int32(expected) {
		t.Errorf("Failed by returning wrong value - %d instead of %d!", result, expected)
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

func TestMissingExample(t *testing.T) {
	expected := int32(1)
	result := JumpingOnClouds([]int32{0, 0})
	if result != expected {
		t.Errorf("Failed by returning wrong value - %d instead of %d!", result, expected)
	}
}

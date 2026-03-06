package encryption

import (
	"fmt"
	"os"
	"path/filepath"
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
	plain := ioLines[0][0][0]
	result := Encryption(plain)
	expected := strings.Join(ioLines[1][0], " ")
	if result != expected {
		t.Errorf("Failed by returning wrong value - %v instead of %v!", result, expected)
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

func TestExample2(t *testing.T) {
	Runner(t, "_example_2")
}

func TestExample3(t *testing.T) {
	Runner(t, "_example_3")
}

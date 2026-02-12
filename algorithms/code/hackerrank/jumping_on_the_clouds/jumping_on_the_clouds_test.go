package jumpingontheclouds

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for index, template := range []string{"input%s.txt", "output%s.txt"} {
		path := fmt.Sprintf(template, name)
		file, fail := os.Open(path)
		if fail != nil {
			t.Fatalf("Failed opening file %s: %s", path, fail)
		}
		defer file.Close()
		lines := make([][]string, 0)
		ioLines[index] = lines
		reader := bufio.NewReader(file)
		for {
			var buffer bytes.Buffer
			var raw []byte
			var prefix bool
			for {
				raw, prefix, fail = reader.ReadLine()
				buffer.Write(raw)
				if !prefix || fail != nil {
					break
				}
			}
			ioLines[index] = append(ioLines[index], strings.Split(strings.TrimSpace(buffer.String()), " "))
			if fail == io.EOF {
				break
			} else if fail != nil {
				t.Fatalf("Failed reading file %s: %s", path, fail)
			}
		}
	}
	count, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	c := make([]int32, count)
	for i, textual := range ioLines[0][1] {
		converted, _ := strconv.ParseInt(textual, 10, 32)
		c[i] = int32(converted)
	}
	result := JumpingOnClouds(c)
	converted, _ := strconv.ParseInt(ioLines[1][0][0], 10, 32)
	expected := int32(converted)
	if result != expected {
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

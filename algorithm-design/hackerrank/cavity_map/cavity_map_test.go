package cavitymap

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"os"
	"reflect"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][]string, 2)
	for index, template := range []string{"input%s.txt", "output%s.txt"} {
		path := fmt.Sprintf(template, name)
		file, fail := os.Open(path)
		if fail != nil {
			t.Fatalf("Failed opening file %s: %s", path, fail)
		}
		defer file.Close()
		lines := make([]string, 0)
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
			ioLines[index] = append(ioLines[index], strings.TrimSpace(buffer.String()))
			if fail == io.EOF {
				break
			} else if fail != nil {
				t.Fatalf("Failed reading file %s: %s", path, fail)
			}
		}
	}
	convertedN, _ := strconv.ParseInt(ioLines[0][0], 10, 32)
	n := int(convertedN)
	result := CavityMap(ioLines[0][1 : 1+n])
	if reflect.DeepEqual(result, ioLines[1]) {
		t.Errorf("CavityMap failed by returning wrong results - %v instead of %v!", result, ioLines[1])
	}
}

func TestExample(t *testing.T) {
	Runner(t, "_example")
}

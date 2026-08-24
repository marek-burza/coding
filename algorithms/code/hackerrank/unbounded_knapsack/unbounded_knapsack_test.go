package unboundedknapsack

import (
	"fmt"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"testing"
)

func generalizedTest(t *testing.T, which string) {
	ioLines := make([][][]string, 2)
	for i, template := range []string{"input%s.txt", "output%s.txt"} {
		data, _ := os.ReadFile(filepath.Clean(fmt.Sprintf(template, which)))
		for line := range strings.SplitSeq(strings.TrimSpace(string(data)), "\n") {
			ioLines[i] = append(ioLines[i], strings.Fields(line))
		}
	}
	count, _ := strconv.Atoi(ioLines[0][0][0])
	offset := 1
	for i := range count {
		k, _ := strconv.Atoi(ioLines[0][offset][1])
		var arr []int
		for _, textual := range ioLines[0][offset+1] {
			converted, _ := strconv.Atoi(textual)
			arr = append(arr, converted)
		}
		offset += 2
		result := UnboundedKnapsack(k, arr)
		expected, _ := strconv.Atoi(ioLines[1][i][0])
		if expected != result {
			t.Errorf("UnboundedKnapsack - Expected %v, got %v!", expected, result)
		}
	}
}

func TestExample(t *testing.T) {
	generalizedTest(t, "example")
}

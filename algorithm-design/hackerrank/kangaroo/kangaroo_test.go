package kangaroo

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	inPath := fmt.Sprintf("input%s.txt", name)
	input, fail := os.Open(inPath)
	if fail != nil {
		t.Fatalf("Failed opening input file: %s", fail)
	}
	inScanner := bufio.NewScanner(input)
	inScanner.Split(bufio.ScanLines)
	var inLines []string
	for inScanner.Scan() {
		inLines = append(inLines, inScanner.Text())
	}
	line := strings.Split(inLines[0], " ")
	x1, _ := strconv.ParseInt(line[0], 10, 32)
	v1, _ := strconv.ParseInt(line[1], 10, 32)
	x2, _ := strconv.ParseInt(line[2], 10, 32)
	v2, _ := strconv.ParseInt(line[3], 10, 32)
	result := Kangaroo(int32(x1), int32(v1), int32(x2), int32(v2))
	outPath := fmt.Sprintf("output%s.txt", name)
	output, fail := os.Open(outPath)
	if fail != nil {
		t.Fatalf("Failed opening output file: %s", fail)
	}
	outScanner := bufio.NewScanner(output)
	outScanner.Split(bufio.ScanLines)
	var outLines []string
	for outScanner.Scan() {
		outLines = append(outLines, outScanner.Text())
	}
	expected := outLines[0]
	if result != expected {
		t.Errorf("Failed by returning wrong value - %s instead of %s!", result, expected)
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

func TestSame(t *testing.T) {
	if "YES" != Kangaroo(1, 2, 1, 2) {
		t.Errorf("Failed with the same values!")
	}
}

func Test00(t *testing.T) {
	Runner(t, "00")
}

func Test01(t *testing.T) {
	Runner(t, "01")
}

func Test02(t *testing.T) {
	Runner(t, "02")
}

func Test03(t *testing.T) {
	Runner(t, "03")
}

func Test04(t *testing.T) {
	Runner(t, "04")
}

func Test05(t *testing.T) {
	Runner(t, "05")
}

func Test06(t *testing.T) {
	Runner(t, "06")
}

func Test07(t *testing.T) {
	Runner(t, "07")
}

func Test08(t *testing.T) {
	Runner(t, "08")
}

func Test09(t *testing.T) {
	Runner(t, "09")
}

func Test10(t *testing.T) {
	Runner(t, "10")
}

func Test11(t *testing.T) {
	Runner(t, "11")
}

func Test12(t *testing.T) {
	Runner(t, "12")
}

func Test13(t *testing.T) {
	Runner(t, "13")
}

func Test14(t *testing.T) {
	Runner(t, "14")
}

func Test15(t *testing.T) {
	Runner(t, "15")
}

func Test16(t *testing.T) {
	Runner(t, "16")
}

func Test17(t *testing.T) {
	Runner(t, "17")
}

func Test18(t *testing.T) {
	Runner(t, "18")
}

func Test19(t *testing.T) {
	Runner(t, "19")
}

func Test20(t *testing.T) {
	Runner(t, "20")
}

func Test21(t *testing.T) {
	Runner(t, "21")
}

func Test22(t *testing.T) {
	Runner(t, "22")
}

func Test23(t *testing.T) {
	Runner(t, "23")
}

func Test24(t *testing.T) {
	Runner(t, "24")
}

func Test25(t *testing.T) {
	Runner(t, "25")
}

func Test26(t *testing.T) {
	Runner(t, "26")
}

func Test27(t *testing.T) {
	Runner(t, "27")
}

func Test28(t *testing.T) {
	Runner(t, "28")
}

func Test29(t *testing.T) {
	Runner(t, "29")
}

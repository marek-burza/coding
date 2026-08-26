package lc187

import (
	"reflect"
	"sort"
	"testing"
)

func generic(t *testing.T, expected []string, result []string) {
	sort.Strings(result)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("FindRepeatedDNASequences - Expected %v, got %v!", expected, result)
	}
}

func TestAAAAACCCCCAAAAACCCCCCAAAAAGGGTTT(t *testing.T) {
	expected := []string{"AAAAACCCCC", "CCCCCAAAAA"}
	generic(t, expected, findRepeatedDNASequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"))
}

func TestNothing(t *testing.T) {
	result := findRepeatedDNASequences("")
	if len(result) != 0 {
		t.Errorf("FindRepeatedDNASequences - Expected nothing, got %v!", result)
	}
}

func TestAAAAAAAAAAAAA(t *testing.T) {
	expected := []string{"AAAAAAAAAA"}
	generic(t, expected, findRepeatedDNASequences("AAAAAAAAAAAAA"))
}

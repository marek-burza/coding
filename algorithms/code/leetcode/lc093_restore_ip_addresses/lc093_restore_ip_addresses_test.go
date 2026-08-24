package lc093

import (
	"reflect"
	"sort"
	"testing"
)

func generic(t *testing.T, expected []string, result []string) {
	sort.Strings(result)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("RestoreIPAddresses - Expected %v, got %v!", expected, result)
	}
}

func Test25525511135(t *testing.T) {
	expected := []string{"255.255.11.135", "255.255.111.35"}
	generic(t, expected, restoreIPAddresses("25525511135"))
}

func Test101023(t *testing.T) {
	expected := []string{
		"1.0.10.23",
		"1.0.102.3",
		"10.1.0.23",
		"10.10.2.3",
		"101.0.2.3",
	}
	generic(t, expected, restoreIPAddresses("101023"))
}

package lc299

import "testing"

func generic(t *testing.T, result string, expected string) {
	if expected != result {
		t.Errorf("GetHint - Expected %v, got %v!", expected, result)
	}
}

func Test1807And7810(t *testing.T) {
	generic(t, getHint("1807", "7810"), "1A3B")
}

func Test1123And0111(t *testing.T) {
	generic(t, getHint("1123", "0111"), "1A1B")
}

func Test1122And2211(t *testing.T) {
	generic(t, getHint("1122", "2211"), "0A4B")
}

func Test11And10(t *testing.T) {
	generic(t, getHint("11", "10"), "1A0B")
}

package lc374

import (
	"math"
	"testing"
)

func generic(t *testing.T, n int, expected int) {
	lc374NumberHigherOrLower = expected
	result := guessNumber(n)
	if expected != result {
		t.Errorf("GuessNumber - Expected %v, got %v!", expected, result)
	}
}

func Test2In10(t *testing.T) {
	generic(t, 10, 2)
}

func Test8In10(t *testing.T) {
	generic(t, 10, 8)
}

func Test65789(t *testing.T) {
	generic(t, math.MaxInt32, 65789)
}

func Test1(t *testing.T) {
	generic(t, 1, 1)
}

package lc070

import "testing"

func Test20(t *testing.T) {
	expected := 10946
	result := climbStairs(20)
	if expected != result {
		t.Errorf("ClimbStairs - Expected %v, got %v!", expected, result)
	}
}

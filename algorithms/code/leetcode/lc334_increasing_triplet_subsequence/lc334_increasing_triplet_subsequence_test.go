package lc334

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IncreasingTriplet - Expected %v, got %v!", expected, result)
	}
}

func TestEmpty(t *testing.T) {
	generic(t, increasingTriplet([]int{}), false)
}

func TestExample1(t *testing.T) {
	generic(t, increasingTriplet([]int{1, 2, 3, 4, 5}), true)
}

func TestExample2(t *testing.T) {
	generic(t, increasingTriplet([]int{5, 4, 3, 2, 1}), false)
}

func TestOther(t *testing.T) {
	generic(t, increasingTriplet([]int{1, 2, 3, 1, 2, 1}), true)
}

func TestNothing(t *testing.T) {
	generic(t, increasingTriplet([]int{}), false)
	generic(t, increasingTriplet([]int{0, 1}), false)
}

func Test516(t *testing.T) {
	generic(t, increasingTriplet([]int{5, 1, 6}), false)
}

func Test24Minus2Minus3(t *testing.T) {
	generic(t, increasingTriplet([]int{2, 4, -2, -3}), false)
}

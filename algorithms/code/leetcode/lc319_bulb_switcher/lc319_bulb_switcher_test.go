package lc319

import "testing"

func Test1To16(t *testing.T) {
	expected := []int{0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4}
	for i, expectedI := range expected {
		result := bulbSwitch(i)
		if expectedI != result {
			t.Errorf("BulbSwitch - Expected %v, got %v!", expectedI, result)
		}
	}
}

package tutorialintro

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	if !reflect.DeepEqual([]float64{1.0 / 2.0, 1.0 / 3.0, 1.0 / 6.0}, PlusMinus([]int32{-4, 3, -9, 0, 4, 1})) {
		t.Errorf("PlusMinus failed with -4, 3, -9, 0, 4, 1!")
	}
}

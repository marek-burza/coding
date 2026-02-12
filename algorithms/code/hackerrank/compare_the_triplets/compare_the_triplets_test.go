package comparethetriplets

import (
	"reflect"
	"testing"
)

func TestExample0(t *testing.T) {
	if !reflect.DeepEqual([]int32{1, 1}, CompareTriplets([]int32{5, 6, 7}, []int32{3, 6, 10})) {
		t.Errorf("CompareTriplets failed with the example!")
	}
}

func TestExample1(t *testing.T) {
	if !reflect.DeepEqual([]int32{2, 1}, CompareTriplets([]int32{17, 28, 30}, []int32{99, 16, 8})) {
		t.Errorf("CompareTriplets failed with the example!")
	}
}

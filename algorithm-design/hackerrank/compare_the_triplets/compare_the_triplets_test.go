package comparethetriplets

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	if !reflect.DeepEqual([]int32{1, 1}, CompareTriplets([]int32{5, 6, 7}, []int32{3, 6, 10})) {
		t.Errorf("CompareTriplets failed with the example!")
	}
}

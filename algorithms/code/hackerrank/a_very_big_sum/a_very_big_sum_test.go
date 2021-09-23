package averybigsum

import (
	"testing"
)

func TestExample(t *testing.T) {
	ar := []int32{1000000001, 1000000002, 1000000003, 1000000004, 1000000005}
	result := AVeryBigSum(ar)
	if result != 5000000015 {
		t.Errorf("AVeryBigSum failed the example test")
	}
}

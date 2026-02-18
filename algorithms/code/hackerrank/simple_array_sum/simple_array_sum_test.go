package simplearraysum

import (
	"testing"
)

func TestExample(t *testing.T) {
	if SimpleArraySum([]int32{1, 2, 3, 4, 10, 11}) != 31 {
		t.Errorf("SimpleArraySum failed with 1, 2, 3, 4, 10!")
	}
}

package timeconversion

import (
	"testing"
)

func Test070545PM(t *testing.T) {
	if "19:05:45" != ToMilitary("07:05:45PM") {
		t.Errorf("ToMilitary failed with 07:05:45PM!")
	}
}

func Test120000PM(t *testing.T) {
	if "12:00:00" != ToMilitary("12:00:00PM") {
		t.Errorf("ToMilitary failed with 07:05:45PM!")
	}
}

func Test120000AM(t *testing.T) {
	if "00:00:00" != ToMilitary("12:00:00AM") {
		t.Errorf("ToMilitary failed with 07:05:45PM!")
	}
}

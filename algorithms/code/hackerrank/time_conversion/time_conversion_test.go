package timeconversion

import (
	"testing"
)

func Test070545PM(t *testing.T) {
	if ToMilitary("07:05:45PM") != "19:05:45" {
		t.Errorf("ToMilitary failed with 07:05:45PM!")
	}
}

func Test120000PM(t *testing.T) {
	if ToMilitary("12:00:00PM") != "12:00:00" {
		t.Errorf("ToMilitary failed with 07:05:45PM!")
	}
}

func Test120000AM(t *testing.T) {
	if ToMilitary("12:00:00AM") != "00:00:00" {
		t.Errorf("ToMilitary failed with 07:05:45PM!")
	}
}

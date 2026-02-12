package timeconversion

// https://www.hackerrank.com/challenges/time-conversion

import (
	"fmt"
	"strconv"
)

// ToMilitary - implements the solution to the problem
func ToMilitary(s string) string {
	runes := []rune(s)
	afternoon := runes[8] == 'P'
	hour := s[0:2]
	if hour == "12" {
		afternoon = !afternoon
	}
	shift := 0
	if afternoon {
		shift = 12
	}
	// nolint:errcheck
	before, _ := strconv.Atoi(hour)
	after := fmt.Sprintf("%02d", (before+shift)%24)
	return after + s[2:8]
}

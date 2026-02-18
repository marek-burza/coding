// Package thetimeinwords implements https://www.hackerrank.com/challenges/the-time-in-words
package thetimeinwords

var lut = []string{
	"zero", "one", "two", "three", "four", "five",
	"six", "seven", "eight", "nine", "ten",
	"eleven", "twelve", "thirteen", "fourteen", "fifteen",
	"sixteen", "seventeen", "eighteen", "nineteen", "twenty",
	"twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
	"twenty six", "twenty seven", "twenty eight", "twenty nine", "thirty",
}

// TimeInWords - implements the solution to the problem
func TimeInWords(h, m int32) string {
	result := ""
	if m != 0 {
		switch {
		case m == 30:
			result += "half past "
		case m == 15:
			result += "quarter past "
		case m == 45:
			result += "quarter to "
			h = (h + 1) % 12
		case m < 30:
			result += lut[m]
			if m == 1 {
				result += " minute past "
			} else {
				result += " minutes past "
			}
		case m > 30:
			m = 60 - m
			result += lut[m]
			if m == 1 {
				result += " minute to "
			} else {
				result += " minutes to "
			}
			h = (h + 1) % 12
		}
	}
	result += lut[h]
	if m == 0 {
		result += " o' clock"
	}
	return result
}

package cavitymap

// https://www.hackerrank.com/challenges/the-time-in-words

var lut []string = []string{
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
		if m == 30 {
			result += "half past "
		} else if m == 15 {
			result += "quarter past "
		} else if m == 45 {
			result += "quarter to "
			h = (h + 1) % 12
		} else if m < 30 {
			result += lut[m]
			if m == 1 {
				result += " minute past "
			} else {
				result += " minutes past "
			}
		} else if m > 30 {
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

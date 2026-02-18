// Package funnystring implements https://www.hackerrank.com/challenges/funny-string
package funnystring

func absDiff(ar, br rune) uint16 {
	a := uint16(ar)
	b := uint16(br)
	if a < b {
		return b - a
	}
	return a - b
}

// FunnyString - implements the solution to the problem
func FunnyString(s string) string {
	sr := []rune(s)
	n := len(s)
	for i := 1; i < n; i++ {
		forward := absDiff(sr[i], sr[i-1])
		backward := absDiff(sr[n-i-1], sr[n-i])
		if forward != backward {
			return "Not Funny"
		}
	}
	return "Funny"
}

// Package funnystring implements https://www.hackerrank.com/challenges/funny-string
package funnystring

func absRune(r rune) rune {
	if r < 0 {
		return -r
	}
	return r
}

// FunnyString - implements the solution to the problem
func FunnyString(s string) string {
	sr := []rune(s)
	n := len(s)
	for i := 1; i < n; i++ {
		forward := absRune(sr[i] - sr[i-1])
		backward := absRune(sr[n-i-1] - sr[n-i])
		if forward != backward {
			return "Not Funny"
		}
	}
	return "Funny"
}

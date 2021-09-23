package angryprofessor

// https://www.hackerrank.com/challenges/angry-professor

// AngryProfessor - implements the solution to the problem
func AngryProfessor(k int, a []int) string {
	absent := 0
	n := len(a)
	threshold := n - k
	for _, arrival := range a {
		if arrival > 0 {
			absent++
		}
		if threshold < absent {
			return "YES"
		}
	}
	return "NO"
}

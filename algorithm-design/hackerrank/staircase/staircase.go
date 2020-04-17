package staircase

// https://www.hackerrank.com/challenges/staircase

// Staircase - implements the solution to the problem
func Staircase(n int32) []string {
	result := make([]string, 0)
	for index := 0; index < int(n); index++ {
		line := ""
		for i := 0; i < int(n); i++ {
			if i < int(n)-1-index {
				line += " "
			} else {
				line += "#"
			}
		}
		result = append(result, line)
	}
	return result
}

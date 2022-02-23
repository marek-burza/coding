package cavitymap

// https://www.hackerrank.com/challenges/manasa-and-stones

// Stones - implements the solution to the problem
func Stones(n int32, a int32, b int32) []int32 {
	result := make([]int32, 0)
	if a > b {
		a, b = b, a
	}
	current := a * (n - 1)
	delta := b - a
	result = append(result, current)
	if a != b {
		for i := 0; int32(i) < n-1; i++ {
			current += delta
			result = append(result, current)
		}
	}
	return result
}

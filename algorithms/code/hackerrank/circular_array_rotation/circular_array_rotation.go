package circulararrayrotation

// https://www.hackerrank.com/challenges/circular-array-rotation

// CircularArrayRotation - implements the solution to the problem
func CircularArrayRotation(k int, a []int, queries []int) []int {
	n := len(a)
	results := make([]int, 0)
	for _, query := range queries {
		results = append(results, a[(query+n-(k%n))%n])
	}
	return results
}

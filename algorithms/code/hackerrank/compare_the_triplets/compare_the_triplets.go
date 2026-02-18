// Package comparethetriplets implements https://www.hackerrank.com/challenges/compare-the-triplets
package comparethetriplets

// CompareTriplets - implements the solution to the problem
func CompareTriplets(a []int32, b []int32) []int32 {
	result := []int32{0, 0}
	for i := range 3 {
		if a[i] > b[i] {
			result[0]++
		}
		if a[i] < b[i] {
			result[1]++
		}
	}
	return result
}

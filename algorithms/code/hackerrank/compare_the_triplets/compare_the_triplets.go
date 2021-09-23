package comparethetriplets

// https://www.hackerrank.com/challenges/compare-the-triplets

// CompareTriplets - implements the solution to the problem
func CompareTriplets(a []int32, b []int32) []int32 {
	result := []int32{0, 0}
	for i := 0; i < 3; i++ {
		if a[i] > b[i] {
			result[0]++
		}
		if a[i] < b[i] {
			result[1]++
		}
	}
	return result
}

// Package averybigsum implements https://www.hackerrank.com/challenges/a-very-big-sum
package averybigsum

// AVeryBigSum - implements the solution to the problem
func AVeryBigSum(ar []int32) int64 {
	var sum int64
	for _, value := range ar {
		sum += int64(value)
	}
	return sum
}

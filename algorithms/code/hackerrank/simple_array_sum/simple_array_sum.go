// Package simplearraysum implements https://www.hackerrank.com/challenges/simple-array-sum
package simplearraysum

// SimpleArraySum - implements the solution to the problem
func SimpleArraySum(ar []int32) int32 {
	var sum int32
	for _, value := range ar {
		sum += value
	}
	return sum
}

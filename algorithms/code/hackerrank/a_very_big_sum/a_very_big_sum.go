package averybigsum

// https://www.hackerrank.com/challenges/a-very-big-sum

// AVeryBigSum - implements the solution to the problem
func AVeryBigSum(ar []int32) int64 {
	var sum int64
	for _, value := range ar {
		sum += int64(value)
	}
	return sum
}

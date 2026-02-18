package sherlockandarray

// https://www.hackerrank.com/challenges/sherlock-and-array

// BalancedSums - implements the solution to the problem
func BalancedSums(arr []int32) string {
	left := make([]int32, len(arr))
	right := make([]int32, len(arr))
	for i := 1; i < len(arr); i++ {
		left[i] = left[i-1] + arr[i-1]
		right[len(arr)-1-i] = right[len(arr)-i] + arr[len(arr)-i]
	}
	for i := range arr {
		if left[i] == right[i] {
			return "YES"
		}
	}
	return "NO"
}

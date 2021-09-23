package diagonaldifference

// https://www.hackerrank.com/challenges/diagonal-difference

// DiagonalDifference - implements the solution to the problem
func DiagonalDifference(arr [][]int32) int32 {
	var result int32 = 0
	n := len(arr)
	for i := 0; i < n; i++ {
		result += arr[i][i] - arr[n-1-i][i]
	}
	if result < 0 {
		result = -result
	}
	return result
}

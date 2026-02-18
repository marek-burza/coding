// Package diagonaldifference implements https://www.hackerrank.com/challenges/diagonal-difference
package diagonaldifference

// DiagonalDifference - implements the solution to the problem
func DiagonalDifference(arr [][]int32) int32 {
	var result int32
	n := len(arr)
	for i := range n {
		result += arr[i][i] - arr[n-1-i][i]
	}
	if result < 0 {
		result = -result
	}
	return result
}

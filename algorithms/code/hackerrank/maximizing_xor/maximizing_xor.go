// Package maximizingxor implements https://www.hackerrank.com/challenges/maximizing-xor
package maximizingxor

// MaximizingXor - implements the solution to the problem
func MaximizingXor(l int32, r int32) int32 {
	maxed := l ^ r
	maxed |= maxed >> 1
	maxed |= maxed >> 2
	maxed |= maxed >> 4
	maxed |= maxed >> 8
	return maxed
}

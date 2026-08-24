// Package unboundedknapsack implements https://www.hackerrank.com/challenges/unbounded-knapsack
package unboundedknapsack

// UnboundedKnapsack - implements the solution to the problem
func UnboundedKnapsack(w int, values []int) int {
	weights := values // Special case
	n := len(values)
	m := make([]int, w+1)
	for wi := range w + 1 {
		for vi := range n {
			if weights[vi] <= wi {
				m[wi] = max(m[wi], m[wi-weights[vi]]+values[vi])
			}
		}
	}
	return m[w]
}

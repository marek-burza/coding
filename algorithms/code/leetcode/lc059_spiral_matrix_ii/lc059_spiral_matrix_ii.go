// Package lc059 implements https://leetcode.com/problems/spiral-matrix-ii/
// #medium
package lc059

func generateMatrix(n int) [][]int {
	limits := []int{n - 1, n - 1, 0, 0}
	restrict := []int{1, -1, -1, 1}
	delta := [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	indices := []int{0, -1}
	matrix := make([][]int, n)
	for i := range matrix {
		matrix[i] = make([]int, n)
	}
	value := 1
	stage := 0
	index := 1
	for limits[0] >= limits[2] && limits[1] >= limits[3] {
		condition := true
		for condition {
			indices[0] += delta[stage][0]
			indices[1] += delta[stage][1]
			matrix[indices[0]][indices[1]] = value
			value++
			condition = indices[index] != limits[stage]
		}
		limits[(stage+3)%4] += restrict[stage]
		stage = (stage + 1) % 4
		index = (index + 1) % 2
	}
	return matrix
}

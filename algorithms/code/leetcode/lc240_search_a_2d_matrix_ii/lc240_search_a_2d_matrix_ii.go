// Package lc240 implements https://leetcode.com/problems/search-a-2d-matrix-ii/
// #medium
package lc240

func searchMatrixInternal(matrix [][]int, target int, rowA int, rowZ int, colA int, colZ int) bool {
	if rowA == rowZ && colA == colZ {
		return matrix[rowZ][colZ] == target
	}
	// if target < matrix[rowA][colA] || matrix[rowZ][colZ] < target {
	//     return false
	// }
	rowM := (rowA + rowZ) / 2
	colM := (colA + colZ) / 2
	cols := colA < colZ
	rows := rowA < rowZ
	if target <= matrix[rowM][colM] && searchMatrixInternal(matrix, target, rowA, rowM, colA, colM) {
		return true
	}
	if cols && target <= matrix[rowM][colZ] && searchMatrixInternal(matrix, target, rowA, rowM, colM+1, colZ) {
		return true
	}
	if rows && target <= matrix[rowZ][colM] && searchMatrixInternal(matrix, target, rowM+1, rowZ, colA, colM) {
		return true
	}
	if !rows || !cols {
		return false
	}
	return searchMatrixInternal(matrix, target, rowM+1, rowZ, colM+1, colZ)
}

func searchMatrix(matrix [][]int, target int) bool {
	return searchMatrixInternal(matrix, target, 0, len(matrix)-1, 0, len(matrix[0])-1)
}

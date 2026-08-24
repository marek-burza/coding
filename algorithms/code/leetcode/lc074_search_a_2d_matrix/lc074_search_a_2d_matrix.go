// Package lc074 implements https://leetcode.com/problems/search-a-2d-matrix/
// #medium
package lc074

func searchMatrix(matrix [][]int, target int) bool {
	ra := 0
	rz := len(matrix) - 1
	for ra < rz {
		rm := 1 + (ra+rz)/2
		if target >= matrix[rm][0] {
			ra = rm
		} else {
			rz = rm - 1
		}
	}
	// if rz < 0 {
	//     return false
	// }
	ca := 0
	cz := len(matrix[ra]) - 1
	for ca < cz {
		cm := 1 + (ca+cz)/2
		if target == matrix[ra][cm] {
			return true
		}
		if target > matrix[ra][cm] {
			ca = cm + 1
		} else {
			cz = cm - 1
		}
	}
	return ca == cz && target == matrix[ra][ca]
}

// Package lc036 implements https://leetcode.com/problems/valid-sudoku/
// #medium
package lc036

func validate(board [][]string, indices [][]int, dx int, dy int) bool {
	check := 0
	for _, at := range indices {
		spot := board[at[1]+dy][at[0]+dx]
		if spot == "." {
			continue
		}
		mask := 1 << (spot[0] - '0')
		if (check & mask) == 0 {
			check |= mask
		} else {
			return false
		}
	}
	return true
}

func isValidSudoku(board [][]string) bool {
	row := [][]int{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}}
	column := [][]int{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}}
	block := [][]int{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}
	for i := range 9 {
		if !validate(board, row, 0, i) {
			return false
		}
		if !validate(board, column, i, 0) {
			return false
		}
		if !validate(board, block, 3*(i/3), 3*(i%3)) {
			return false
		}
	}
	return true
}

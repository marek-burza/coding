// Package lc289 implements https://leetcode.com/problems/game-of-life/
// #medium
package lc289

func count(board [][]int, row int, col int) int {
	if 0 <= row && row < len(board) && 0 <= col && col < len(board[row]) {
		return board[row][col] & 1
	}
	return 0
}

func countAlive(board [][]int, row int, col int) int {
	counted := 0
	counted += count(board, row-1, col-1)
	counted += count(board, row-1, col)
	counted += count(board, row-1, col+1)
	counted += count(board, row, col-1)
	counted += count(board, row, col+1)
	counted += count(board, row+1, col-1)
	counted += count(board, row+1, col)
	counted += count(board, row+1, col+1)
	return counted
}

func gameOfLife(board [][]int) {
	for row := range board {
		for col := range board[row] {
			count := countAlive(board, row, col)
			mask := 0
			if (board[row][col] & 1) == 1 {
				if count >= 2 && count <= 3 {
					mask = 2
				}
			} else if count == 3 {
				mask = 2
			}
			board[row][col] |= mask
		}
	}
	for row := range board {
		for col := range board[row] {
			board[row][col] >>= 1
		}
	}
}

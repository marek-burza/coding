// Package lc130 implements https://leetcode.com/problems/surrounded-regions/
// #medium
package lc130

func key(i int, j int) int64 {
	return (int64(i) << 32) | int64(j)
}

func keyI(key int64) int {
	return int(key >> 32)
}

func keyJ(key int64) int {
	return int(key & 0xFFFFFFFF)
}

func enqueue(i int, j int, check *[]int64, visited map[int64]struct{}) {
	current := key(i, j)
	if _, found := visited[current]; !found {
		*check = append(*check, current)
		visited[current] = struct{}{}
	}
}

func solve(board [][]byte) {
	if len(board) == 0 {
		return
	}
	for _, row := range board {
		if len(row) == 0 {
			return
		}
	}
	var check []int64
	visited := make(map[int64]struct{})
	for i, boardI := range board {
		enqueue(i, 0, &check, visited)
		enqueue(i, len(boardI)-1, &check, visited)
	}
	for j := range board[0] {
		enqueue(0, j, &check, visited)
	}
	for j := range board[len(board)-1] {
		enqueue(len(board)-1, j, &check, visited)
	}
	for len(check) > 0 {
		key := check[0]
		check = check[1:]
		i := keyI(key)
		j := keyJ(key)
		if i < 0 || j < 0 || len(board) <= i || len(board[i]) <= j {
			continue
		}
		if board[i][j] == 'O' {
			board[i][j] = 'V'
			enqueue(i+1, j, &check, visited)
			enqueue(i-1, j, &check, visited)
			enqueue(i, j+1, &check, visited)
			enqueue(i, j-1, &check, visited)
		}
	}
	for i := range board {
		for j := range board[i] {
			switch board[i][j] {
			case 'O':
				board[i][j] = 'X'
			case 'V':
				board[i][j] = 'O'
			}
		}
	}
}

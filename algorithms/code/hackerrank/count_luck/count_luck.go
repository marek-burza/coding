// Package countluck implements https://www.hackerrank.com/challenges/count-luck
package countluck

type here struct {
	row int
	col int
}

func lookAround(forest [][]rune, at here) []here {
	deltas := [][]int{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}
	ways := make([]here, 0)
	for _, delta := range deltas {
		dr := at.row + delta[0]
		dc := at.col + delta[1]
		if dr < 0 || len(forest) <= dr {
			continue
		}
		if dc < 0 || len(forest[dr]) <= dc {
			continue
		}
		if forest[dr][dc] == 'X' {
			continue
		}
		ways = append(ways, here{dr, dc})
	}
	return ways
}

const oops = "Oops!"

// CountLuck - implements the solution to the problem
func CountLuck(matrix []string, k int32) string {
	queue := make([]here, 0)
	counts := make([]int32, 0)
	counts = append(counts, 0)
	forest := make([][]rune, len(matrix))
	for r := range matrix {
		forest[r] = []rune(matrix[r])
		for c := 0; c < len(forest[r]); c++ {
			if forest[r][c] == 'M' {
				queue = append(queue, here{r, c})
			}
		}
	}
	for len(queue) != 0 {
		at := queue[0]
		queue = queue[1:]
		count := counts[0]
		counts = counts[1:]
		if forest[at.row][at.col] == '*' {
			if count == k {
				return "Impressed"
			}
			return oops
		}
		forest[at.row][at.col] = 'X'
		ways := lookAround(forest, at)
		for _, way := range ways {
			queue = append(queue, way)
			if len(ways) > 1 {
				counts = append(counts, count+1)
			} else {
				counts = append(counts, count)
			}
		}
	}
	return oops
}

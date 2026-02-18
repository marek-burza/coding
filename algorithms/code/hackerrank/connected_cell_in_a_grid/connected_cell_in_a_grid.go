// Package connectedcellinagrid implements https://www.hackerrank.com/challenges/connected-cell-in-a-grid
package connectedcellinagrid

type here struct {
	row int
	col int
}

func traverse(matrix [][]int32, r int, c int) int32 {
	deltas := [8][2]int{{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}}
	size := int32(0)
	queue := make([]here, 0)
	queue = append(queue, here{r, c})
	for len(queue) > 0 {
		at := queue[0]
		queue = queue[1:]
		r := at.row
		c := at.col
		if matrix[r][c] == 1 {
			matrix[r][c] = -1
			size++
			for _, delta := range deltas {
				rd := delta[0]
				cd := delta[1]
				rn := r + rd
				cn := c + cd
				if rn >= 0 && cn >= 0 && rn < len(matrix) && cn < len(matrix[rn]) {
					queue = append(queue, here{rn, cn})
				}
			}
		}
	}
	return size
}

// ConnectedCell - implements the solution to the problem
func ConnectedCell(matrix [][]int32) int32 {
	result := int32(0)
	for r := range matrix {
		for c := range matrix[r] {
			size := traverse(matrix, r, c)
			if size > result {
				result = size
			}
		}
	}
	return result
}

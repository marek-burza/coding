// Package lc119 implements https://leetcode.com/problems/pascals-triangle-ii/
package lc119

func getRow(rowIndex int) []int {
	rowIndex++
	if rowIndex < 0 {
		return nil
	}
	var previous []int
	var current []int
	for i := range rowIndex {
		current = nil
		current = append(current, 1)
		if i > 0 {
			for j := range i - 1 {
				current = append(current, previous[j]+previous[j+1])
			}
			current = append(current, 1)
		}
		previous = current
	}
	return current
}

// Package lc223 implements https://leetcode.com/problems/rectangle-area/
// #medium
package lc223

func area(left int, bottom int, right int, top int) int {
	return (right - left) * (top - bottom)
}

func computeArea(ax1 int, ay1 int, ax2 int, ay2 int, bx1 int, by1 int, bx2 int, by2 int) int {
	total := area(ax1, ay1, ax2, ay2)
	total += area(bx1, by1, bx2, by2)
	top := min(ay2, by2)
	bottom := max(ay1, by1)
	left := max(ax1, bx1)
	right := min(ax2, bx2)
	if bottom < top && left < right {
		total -= area(left, bottom, right, top)
	}
	return total
}

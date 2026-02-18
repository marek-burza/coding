// Package libraryfine implements https://www.hackerrank.com/challenges/library-fine
package libraryfine

// LibraryFine - implements the solution to the problem
func LibraryFine(d1 int32, m1 int32, y1 int32, d2 int32, m2 int32, y2 int32) int32 {
	if y1 > y2 {
		return 10000
	}
	if y1 == y2 {
		if m1 > m2 {
			return (m1 - m2) * 500
		}
		if m1 == m2 && d1 > d2 {
			return (d1 - d2) * 15
		}
	}
	return 0
}

// Package lc278 implements https://leetcode.com/problems/first-bad-version/
package lc278

var lc278FirstBadVersion = 0

func isBadVersion(version int) bool {
	return lc278FirstBadVersion <= version
}

func firstBadVersion(n int) int {
	a := 1
	z := n
	for a != z {
		i := (a + z) >> 1
		if !isBadVersion(i) {
			a = i + 1
		} else {
			z = i
		}
	}
	return a
}

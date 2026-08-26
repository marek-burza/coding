// Package lc367 implements https://leetcode.com/problems/valid-perfect-square/
package lc367

func isPerfectSquare(num int) bool {
	a := 0
	z := 1 + num/2
	for a <= z {
		m := (a + z) / 2
		mm := m * m
		if mm == num {
			return true
		}
		if mm > num {
			z = m - 1
		} else {
			a = m + 1
		}
	}
	return false
}

// Package lc069 implements https://leetcode.com/problems/sqrtx/
package lc069

func mySqrt(x int) int {
	a := 0
	z := x
	for a+1 < z {
		m := (a + z) >> 1
		mm := m * m
		if mm == x {
			return m
		}
		if mm < x {
			a = m
		} else {
			z = m - 1
		}
	}
	if z*z <= x {
		return z
	}
	return a
}

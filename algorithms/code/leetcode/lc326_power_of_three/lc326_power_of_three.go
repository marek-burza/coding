// Package lc326 implements https://leetcode.com/problems/power-of-three/
// To do it without a loop resort to logarithms (but beware of accuracy)
package lc326

func isPowerOfThree(n int) bool {
	if n < 1 {
		return false
	}
	for n > 1 {
		if n%3 != 0 {
			return false
		}
		n /= 3
	}
	return true
}

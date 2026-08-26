// Package lc374 implements https://leetcode.com/problems/guess-number-higher-or-lower/
package lc374

var lc374NumberHigherOrLower = 0

func guess(num int) int {
	if lc374NumberHigherOrLower < num {
		return -1
	}
	if lc374NumberHigherOrLower > num {
		return 1
	}
	return 0
}

func guessNumber(n int) int {
	a := 1
	z := n
	for a != z {
		checking := (a + z) >> 1
		checked := guess(checking)
		if checked == 0 {
			return checking
		}
		if checked == 1 {
			a = checking + 1
		} else {
			z = checking - 1
		}
	}
	return a
}

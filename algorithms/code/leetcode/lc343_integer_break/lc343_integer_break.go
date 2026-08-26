// Package lc343 implements https://leetcode.com/problems/integer-break/
// #medium
package lc343

func integerBreak(n int) int {
	if n == 2 {
		return 1
	}
	if n == 3 {
		return 2
	}
	if n == 5 {
		return 6
	}
	threes := n / 3
	rest := n - 3*(threes-1)
	if rest == 5 {
		rest = 6
	}
	power := 1
	for range threes - 1 {
		power *= 3
	}
	return power * rest
	// product := 1
	// for n > 4 {
	//     product *= 3
	//     n -= 3
	// }
	// return product * n
}

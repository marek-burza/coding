package lc007

// https://leetcode.com/problems/reverse-integer/

import "math"

func reverse(x int) int {
	if x == math.MinInt32 {
		return 0
	}
	negative := x < 0
	if negative {
		x = -x
	}
	digits := make([]int, 10)
	collected := 0
	for x > 0 {
		digits[collected] = x % 10
		collected++
		x /= 10
	}
	limits := []int{2, 1, 4, 7, 4, 8, 3, 6, 4, 7}
	length := len(limits)
	padding := length - collected
	copy(digits[padding:], digits[:collected])
	for i := range padding {
		digits[i] = 0
	}
	for i := range length {
		if digits[i] > limits[i] {
			return 0
		}
		if digits[i] < limits[i] {
			break
		}
	}
	result := 0
	for _, digit := range digits {
		result = result*10 + digit
	}
	if negative {
		result = -result
	}
	return result
}

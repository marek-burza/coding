// Package lc258 implements https://leetcode.com/problems/add-digits/
package lc258

func addDigits(num int) int {
	for num >= 10 {
		summed := 0
		for num > 0 {
			digit := num % 10
			summed += digit
			num /= 10
		}
		num = summed
	}
	return num
	// return num - 9*(num-1)/9
}

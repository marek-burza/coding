// Package kaprekarnumbers implements https://www.hackerrank.com/challenges/kaprekar-numbers
package kaprekarnumbers

import (
	"math"
	"strconv"
)

// KaprekarNumbers - implements the solution to the problem
func KaprekarNumbers(p int32, q int32) []string {
	found := make([]string, 0)
	for n := int64(p); n <= int64(q); n++ {
		digitsCount := 1 + int64(math.Log10(float64(n)))
		nn := n * n
		splitter := int64(math.Pow(10, float64(digitsCount)))
		r := nn / splitter
		l := nn % splitter
		if n == r+l {
			found = append(found, strconv.Itoa(int(n)))
		}
	}
	if len(found) == 0 {
		return []string{"INVALID", "RANGE"}
	}
	return found
}

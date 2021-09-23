package fibonaccimodified

// https://www.hackerrank.com/challenges/fibonacci-modified

import (
	"math/big"
)

// FibonacciModified - implements the solution to the problem
func FibonacciModified(t1 int32, t2 int32, n int32) *big.Int {
	tn1 := new(big.Int).SetInt64(int64(t1))
	tn2 := new(big.Int).SetInt64(int64(t2))
	for 3 <= n {
		result := new(big.Int).SetInt64(0)
		result.Add(result, tn2)
		result.Mul(result, result)
		result.Add(result, tn1)
		tn1 = tn2
		tn2 = result
		n--
	}
	return tn2
}

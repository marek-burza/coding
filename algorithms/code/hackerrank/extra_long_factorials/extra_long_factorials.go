package extralongfactorials

import "math/big"

// https://www.hackerrank.com/challenges/extra-long-factorials

// ExtraLongFactorials - implements the solution to the problem
func ExtraLongFactorials(n int32) string {
	result := new(big.Int).SetInt64(1)
	for i := 2; int32(i) <= n; i++ {
		result.Mul(result, new(big.Int).SetInt64(int64(i)))
	}
	return result.String()
}

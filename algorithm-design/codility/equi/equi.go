package codilityequi

// https://web.archive.org/web/20170803130133/http://qa.geeksforgeeks.org/6310/find-the-nearest-locker-in-the-city

import (
	"math/big"
)

// FindEquilibriumIndex finds an index where sum of array elemends before index is equal the that of after
func FindEquilibriumIndex(array []int) int {
	n := len(array)
	if n == 0 {
		return -1
	}
	after := make([]*big.Int, n)
	for i := 0; i < n; i++ {
		after[i] = new(big.Int)
	}
	after[n-1].SetInt64(0)
	for i := 1; i < n; i++ {
		after[n-1-i].Add(after[n-i], new(big.Int).SetInt64(int64(array[n-i])))
	}
	summed := new(big.Int)
	summed.SetInt64(0)
	for i := 0; i < n; i++ {
		if summed.Cmp(after[i]) == 0 {
			return i
		}
		summed.Add(summed, new(big.Int).SetInt64(int64(array[i])))
	}
	return -1
}

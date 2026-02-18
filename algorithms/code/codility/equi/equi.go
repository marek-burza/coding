package equi

// http://blog.codility.com/2011/03/solutions-for-task-equi.html

import (
	"math/big"
)

// FindEquilibriumIndex finds the equilibrium index of the given array
func FindEquilibriumIndex(array []int) int {
	n := len(array)
	if n == 0 {
		return -1
	}

	after := make([]*big.Int, n)
	for i := range n {
		after[i] = big.NewInt(0)
	}
	after[n-1] = big.NewInt(0)

	for i := 1; i < n; i++ {
		after[n-1-i] = new(big.Int).Add(after[n-i], big.NewInt(int64(array[n-i])))
	}

	summed := big.NewInt(0)
	for i := range n {
		if summed.Cmp(after[i]) == 0 {
			return i
		}
		summed.Add(summed, big.NewInt(int64(array[i])))
	}
	return -1
}

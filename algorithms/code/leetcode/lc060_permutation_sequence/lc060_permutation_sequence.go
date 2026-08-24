// Package lc060 implements https://leetcode.com/problems/permutation-sequence/
package lc060

import (
	"slices"
	"strconv"
	"strings"
)

func getPermutation(n int, k int) string {
	if n < 0 || k < 0 {
		return ""
	}
	var result strings.Builder
	var remaining []int
	var factorials []int
	factorials = append(factorials, 0)
	factorial := 1
	for i := 1; i <= n; i++ {
		factorial *= i
		factorials = append(factorials, factorial)
		remaining = append(remaining, i)
	}
	for i := 1; i < n; i++ {
		block := factorials[n-i]
		index := (k - 1) / block
		result.WriteString(strconv.Itoa(remaining[index]))
		remaining = slices.Delete(remaining, index, index+1)
		k -= index * block
	}
	result.WriteString(strconv.Itoa(remaining[0]))
	return result.String()
}

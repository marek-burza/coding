// Package lc318 implements https://leetcode.com/problems/maximum-product-of-word-lengths/
// #medium
package lc318

func maxProduct(words []string) int {
	signature := make([]int, len(words))
	for i := range words {
		for j := range len(words[i]) {
			signature[i] |= 1 << (words[i][j] - 'a')
		}
	}
	maximum := 0
	for i := range len(words) - 1 {
		for j := i + 1; j < len(words); j++ {
			if (signature[i] & signature[j]) == 0 {
				maximum = max(maximum, len(words[i])*len(words[j]))
			}
		}
	}
	return maximum
}

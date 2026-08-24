// Package lc047 implements https://leetcode.com/problems/permutations-ii/
// #medium
package lc047

func permuteUnique(nums []int) [][]int {
	// Count each number
	counted := make(map[int]int, len(nums))
	for _, num := range nums {
		counted[num]++
	}
	// Generate the permutations
	var permutations [][]int
	generate([]int{}, len(nums), counted, &permutations)
	return permutations
}

func generate(permutation []int, limit int, counted map[int]int, permutations *[][]int) {
	if len(permutation) == limit {
		*permutations = append(*permutations, append([]int{}, permutation...))
		return
	}
	for key := range counted {
		count := counted[key]
		if count != 0 {
			permutation = append(permutation, key)
			counted[key]--
			generate(permutation, limit, counted, permutations)
			counted[key] = count
			permutation = permutation[:len(permutation)-1]
		}
	}
}

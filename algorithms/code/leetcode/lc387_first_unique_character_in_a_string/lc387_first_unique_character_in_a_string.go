// Package lc387 implements https://leetcode.com/problems/first-unique-character-in-a-string/
package lc387

func firstUniqChar(s string) int {
	size := 'z' - 'a' + 1
	count := make([]int, size)
	index := make([]int, size)
	length := len(s)
	for i := length - 1; i >= 0; i-- {
		key := s[i] - 'a'
		index[key] = i
		count[key]++
	}
	minimum := -1
	for i := range size {
		if count[i] == 1 && (minimum == -1 || index[i] < minimum) {
			minimum = index[i]
		}
	}
	return minimum
}

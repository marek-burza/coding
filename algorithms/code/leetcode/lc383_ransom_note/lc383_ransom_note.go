// Package lc383 implements https://leetcode.com/problems/ransom-note/
package lc383

func canConstruct(ransomNote string, magazine string) bool {
	counts := make([]int, 256)
	for i := range len(magazine) {
		counts[magazine[i]]++
	}
	for i := range len(ransomNote) {
		counts[ransomNote[i]]--
		if counts[ransomNote[i]] < 0 {
			return false
		}
	}
	return true
}

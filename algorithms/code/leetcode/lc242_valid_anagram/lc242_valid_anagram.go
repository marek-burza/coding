// Package lc242 implements https://leetcode.com/problems/valid-anagram/
// #google
package lc242

import (
	"maps"
)

func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	sSet := make(map[byte]struct{})
	for i := range len(s) {
		sSet[s[i]] = struct{}{}
	}
	tSet := make(map[byte]struct{})
	for i := range len(t) {
		tSet[t[i]] = struct{}{}
	}
	return maps.Equal(sSet, tSet)
}

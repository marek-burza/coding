// Package lc290 implements https://leetcode.com/problems/word-pattern/
package lc290

import "strings"

func check(first string, second string, mapping map[string]string) bool {
	if value, found := mapping[first]; found {
		if value != second {
			return false
		}
	} else {
		mapping[first] = second
	}
	return true
}

func wordPattern(pattern string, s string) bool {
	words := strings.Split(s, " ")
	if len(pattern) != len(words) {
		return false
	}
	mappingPs := make(map[string]string)
	mappingSp := make(map[string]string)
	i := 0
	for i < len(words) {
		key := pattern[i : i+1]
		checkPs := check(key, words[i], mappingPs)
		checkSp := check(words[i], key, mappingSp)
		if !checkPs || !checkSp {
			return false
		}
		i++
	}
	return true
}

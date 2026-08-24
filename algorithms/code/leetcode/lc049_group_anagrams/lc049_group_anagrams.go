// Package lc049 implements https://leetcode.com/problems/group-anagrams/
// #medium
package lc049

import (
	"slices"
	"sort"
)

func groupAnagrams(strs []string) [][]string {
	seen := make(map[string][]string)
	for _, str := range strs {
		array := []byte(str)
		slices.Sort(array)
		key := string(array)
		if group, found := seen[key]; found {
			seen[key] = append(group, str)
		} else {
			var group []string
			group = append(group, str)
			seen[key] = group
		}
	}
	var result [][]string
	for _, group := range seen {
		sort.Strings(group)
		result = append(result, group)
	}
	return result
}

// Package lc331 implements https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
// #medium
package lc331

import (
	"strings"
)

func isValidSerialization(preorder string) bool {
	if len(preorder) == 0 {
		return false
	}
	items := strings.Split(preorder, ",")
	kids := []int{1}
	for _, node := range items {
		for kids[len(kids)-1] == 2 {
			kids = kids[:len(kids)-1]
			if len(kids) == 0 {
				return false
			}
		}
		kids[len(kids)-1]++
		if node != "#" {
			kids = append(kids, 0)
		}
	}
	for len(kids) != 0 && kids[len(kids)-1] == 2 {
		kids = kids[:len(kids)-1]
	}
	return len(kids) == 0
}

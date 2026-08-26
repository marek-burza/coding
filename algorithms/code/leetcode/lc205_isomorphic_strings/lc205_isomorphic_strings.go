// Package lc205 implements https://leetcode.com/problems/isomorphic-strings/
package lc205

func isIsomorphic(s string, t string) bool {
	mapped := make(map[byte]byte)
	for i := range len(s) {
		source := s[i]
		target := t[i]
		if mappedSource, found := mapped[source]; found {
			if target != mappedSource {
				return false
			}
		} else {
			for _, value := range mapped {
				if target == value {
					return false
				}
			}
			mapped[source] = target
		}
	}
	return true
}

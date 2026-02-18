// Package lc003 implements https://leetcode.com/problems/longest-substring-without-repeating-characters/
package lc003

func foundInSeen(seen map[byte]struct{}, found byte) bool {
	_, exists := seen[found]
	return exists
}

func lengthOfLongestSubstring(s string) int {
	seen := make(map[byte]struct{})
	longest := 0
	count := 0
	for i := 0; i < len(s); i++ {
		found := s[i]
		for count > 0 && foundInSeen(seen, found) {
			delete(seen, s[i-count])
			count--
		}
		count++
		seen[found] = struct{}{}
		if longest < count {
			longest = count
		}
	}
	return longest
}

package lc028

// https://leetcode.com/problems/implement-strstr/

// StrStr function implements the substring search algorithm.
func StrStr(haystack string, needle string) int {
	if len(haystack) < len(needle) {
		return -1
	}
	for i := 0; i <= len(haystack)-len(needle); i++ {
		if haystack[i:i+len(needle)] == needle {
			return i
		}
	}
	return -1
}

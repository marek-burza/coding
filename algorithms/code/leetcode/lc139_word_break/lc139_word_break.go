// Package lc139 implements https://leetcode.com/problems/word-break/
// #medium
package lc139

func wordBreakInternal(s string, wordDict map[string]struct{}, at int, length int, checked []bool) bool {
	if checked[at] {
		return false
	}
	limit := min(len(s), at+length)
	for i := at + 1; i <= limit; i++ {
		if _, found := wordDict[s[at:i]]; found && (i == len(s) || wordBreakInternal(s, wordDict, i, length, checked)) {
			return true
		}
	}
	checked[at] = true
	return false
}

func wordBreak(s string, wordDict []string) bool {
	length := 0
	checked := make([]bool, len(s))
	words := make(map[string]struct{}, len(wordDict))
	for _, word := range wordDict {
		length = max(length, len(word))
		words[word] = struct{}{}
	}
	return wordBreakInternal(s, words, 0, length, checked)
}

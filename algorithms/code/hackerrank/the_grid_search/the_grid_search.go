package thegridsearch

import (
	"strings"
)

// https://www.hackerrank.com/challenges/the-grid-search

// GridSearch - implements the solution to the problem
func GridSearch(g []string, p []string) string {
	everything := []rune(strings.Join(g, ""))
	at := -1
	for {
		result := strings.Index(string(everything[at+1:]), p[0])
		at = at + 1 + result
		if result == -1 {
			return "NO"
		}
		if (at%len(g[0]))+len(p[0]) > len(g[0]) {
			continue
		}
		offset := at
		ok := true
		for _, chunk := range p {
			if string(everything[offset:offset+len(chunk)]) != chunk {
				ok = false
				break
			}
			offset += len(g[0])
		}
		if ok {
			return "YES"
		}
	}
}

package thegridsearch

import (
	"strings"
)

// https://www.hackerrank.com/challenges/the-grid-search

type here struct {
	row int
	col int
}

// GridSearch - implements the solution to the problem
func GridSearch(G []string, P []string) string {
	everything := []rune(strings.Join(G, ""))
	at := -1
	for {
		result := strings.Index(string(everything[at+1:]), P[0])
		at = at + 1 + result
		if result == -1 {
			return "NO"
		}
		if (at%len(G[0]))+len(P[0]) > len(G[0]) {
			continue
		}
		offset := at
		ok := true
		for _, chunk := range P {
			if string(everything[offset:offset+len(chunk)]) != chunk {
				ok = false
				break
			}
			offset += len(G[0])
		}
		if ok {
			return "YES"
		}
	}
}

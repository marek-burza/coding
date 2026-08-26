// Package lc299 implements https://leetcode.com/problems/bulls-and-cows/
// #medium
package lc299

import "strconv"

func getHint(secret string, guess string) string {
	countKnown := make(map[byte]int)
	countAsked := make(map[byte]int)
	bulls := 0
	cows := 0
	i := 0
	for i < min(len(secret), len(guess)) {
		// Count characters of each type
		known := secret[i]
		asked := guess[i]
		countKnown[known]++
		countAsked[asked]++
		// Check for a bull
		if known == asked {
			bulls++
		}
		i++
	}
	// Count the cows
	for asked, countAskedValue := range countAsked {
		if countKnownValue, found := countKnown[asked]; found {
			cows += min(countKnownValue, countAskedValue)
		}
	}
	// Remove the bulls from the cows
	cows -= bulls
	return "" + strconv.Itoa(bulls) + "A" + strconv.Itoa(cows) + "B"
	// It would have been faster to have one lookup table
	// and update cows up or down accordingly
}

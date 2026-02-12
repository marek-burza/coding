package lc017

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

func letterCombinations(digits string) []string {
	if digits == "" {
		return []string{}
	}
	mapping := []string{
		" ", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz",
	}
	mapped := []string{""}
	for _, digit := range digits {
		index := digit - '0'
		var longer []string
		for _, prefix := range mapped {
			for _, suffix := range mapping[index] {
				longer = append(longer, prefix+string(suffix))
			}
		}
		mapped = longer
	}
	return mapped
}

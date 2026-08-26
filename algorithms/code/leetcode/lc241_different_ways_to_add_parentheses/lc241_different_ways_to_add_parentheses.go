// Package lc241 implements https://leetcode.com/problems/different-ways-to-add-parentheses/
// #medium
package lc241

import (
	"regexp"
	"strconv"
)

var pattern = regexp.MustCompile(`[+\-*]|\d+`)

func diffWaysToCompute(expression string) []int {
	items := pattern.FindAllString(expression, -1)
	cached := make(map[[2]int][]int)
	var traverse func(a int, z int) []int
	traverse = func(a int, z int) []int {
		if result, found := cached[[2]int{a, z}]; found {
			return result
		}
		var result []int
		if a == z {
			value, _ := strconv.Atoi(items[a])
			result = append(result, value)
		} else {
			for operator := a + 1; operator < z; operator += 2 {
				before := traverse(a, operator-1)
				after := traverse(operator+1, z)
				for _, ante := range before {
					for _, post := range after {
						switch items[operator] {
						case "+":
							result = append(result, ante+post)
						case "-":
							result = append(result, ante-post)
						case "*":
							result = append(result, ante*post)
						}
					}
				}
			}
		}
		cached[[2]int{a, z}] = result
		return result
	}
	return traverse(0, len(items)-1)
}

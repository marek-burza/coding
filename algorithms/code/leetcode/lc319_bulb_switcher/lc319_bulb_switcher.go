// Package lc319 implements https://leetcode.com/problems/bulb-switcher/
// #medium
package lc319

import "math"

func bulbSwitch(n int) int {
	return int(math.Sqrt(float64(n)))
}

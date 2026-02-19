// Package jumpingontheclouds implements https://www.hackerrank.com/challenges/jumping-on-the-clouds
package jumpingontheclouds

// JumpingOnClouds - implements the solution to the problem
func JumpingOnClouds(c []int32) int32 {
	count := 0
	for len(c) > 1 {
		if len(c) > 2 && c[2] == 0 {
			c = c[2:]
		} else {
			c = c[1:]
		}
		count++
	}
	return int32(count)
}

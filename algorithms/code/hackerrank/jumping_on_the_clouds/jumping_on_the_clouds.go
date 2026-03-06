// Package jumpingontheclouds implements https://www.hackerrank.com/challenges/jumping-on-the-clouds
package jumpingontheclouds

// JumpingOnClouds - implements the solution to the problem
func JumpingOnClouds(c []int32) int32 {
	n := len(c)
	count := int32(0)
	i := 0
	for i < n-1 {
		if i+2 > n-1 {
			count++
			break
		}
		count += int32(1) + c[i+2]
		i += 2 + int(c[i+2])
	}
	return count
}

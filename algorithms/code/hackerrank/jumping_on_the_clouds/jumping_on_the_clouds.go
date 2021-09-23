package jumpingontheclouds

// https://www.hackerrank.com/challenges/jumping-on-the-clouds

// JumpingOnClouds - implements the solution to the problem
func JumpingOnClouds(c []int32) int32 {
	n := int32(len(c))
	count := int32(0)
	for i := int32(0); i < n-1; {
		if i+2 > n-1 {
			count++
			break
		}
		count += 1 + c[i+2]
		i += 2 + c[i+2]
	}
	return count
}

// Package lc089 implements https://leetcode.com/problems/gray-code/
// #medium
package lc089

func grayCode(bits int) []int {
	if bits == 0 {
		listed := []int{0}
		return listed
	}
	listed := []int{0, 1}
	shifted := 2
	for bits > 1 {
		bits--
		n := len(listed)
		for i := n - 1; i >= 0; i-- {
			value := listed[i]
			listed = append(listed, shifted|value)
		}
		shifted <<= 1
	}
	return listed
}

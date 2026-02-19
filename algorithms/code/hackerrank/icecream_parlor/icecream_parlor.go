// Package icecreamparlor implements https://www.hackerrank.com/challenges/icecream-parlor
package icecreamparlor

// IcecreamParlor - implements the solution to the problem
func IcecreamParlor(m int32, arr []int32) []int32 {
	mapped := make(map[int32]int32)
	idx := int32(0)
	for _, v := range arr {
		mapped[v] = idx
		idx++
	}
	idx = 0
	for _, v := range arr {
		first := idx
		value := m - v
		second, valueInMapped := mapped[value]
		if valueInMapped && first != second {
			return []int32{first + 1, second + 1}
		}
		idx++
	}
	return []int32{0, 0}
}

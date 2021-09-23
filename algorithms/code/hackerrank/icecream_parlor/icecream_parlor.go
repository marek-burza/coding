package icecreamparlor

// https://www.hackerrank.com/challenges/icecream-parlor

// IcecreamParlor - implements the solution to the problem
func IcecreamParlor(m int32, arr []int32) []int32 {
	n := int32(len(arr))
	mapped := make(map[int32]int32)
	for i := int32(0); i < n; i++ {
		mapped[arr[i]] = i
	}
	for i := int32(0); i < n; i++ {
		first := i
		value := m - arr[i]
		_, valueInMapped := mapped[value]
		if valueInMapped {
			second := mapped[value]
			if first == second {
				continue
			}
			return []int32{first + 1, second + 1}
		}
	}
	return []int32{0, 0}
}

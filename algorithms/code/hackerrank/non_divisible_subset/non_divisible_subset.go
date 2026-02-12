package nondivisiblesubset

// https://www.hackerrank.com/challenges/non-divisible-subset

// NonDivisibleSubset - implements the solution to the problem
func NonDivisibleSubset(k int32, s []int32) int32 {
	counted := make(map[int32]int32)
	for _, value := range s {
		rest := value % k
		_, countedContainsRest := counted[rest]
		if countedContainsRest {
			counted[rest]++
		} else {
			counted[rest] = int32(1)
		}
	}
	ok := make(map[int32]struct{})
	exists := struct{}{}
	for a := range counted {
		b := k - a
		if a == 0 || a == b {
			continue
		}
		countB, countedContainsB := counted[b]
		if !countedContainsB {
			ok[a] = exists
		} else {
			countA := counted[a]
			if countA > countB {
				ok[a] = exists
			} else {
				ok[b] = exists
			}
		}
	}
	total := int32(0)
	for a := range ok {
		countA := counted[a]
		total += countA
	}
	_, countedContains0 := counted[0]
	if countedContains0 {
		total++
	}
	_, countedContainsKShiftRight1 := counted[k>>1]
	if (k&1) == 0 && countedContainsKShiftRight1 {
		total++
	}
	return total
}

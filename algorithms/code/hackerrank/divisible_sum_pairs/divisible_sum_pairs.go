package divisiblesumpairs

// https://www.hackerrank.com/challenges/divisible-sum-pairs

func nChooseK(n, k uint32) uint64 {
	result := uint64(1)
	for ki := uint64(0); ki < uint64(k); ki++ {
		result = result * (uint64(n) - ki) / (ki + 1)
	}
	return result
}

// DivisibleSumPairs - implements the solution to the problem
func DivisibleSumPairs(n int32, k int32, ar []int32) int32 {
	counted := make(map[uint32]uint32)
	for _, value := range ar {
		rest := uint32(value % k)
		_, present := counted[rest]
		if present {
			counted[rest]++
		} else {
			counted[rest] = 1
		}
	}
	total := uint64(0)
	covered := make(map[uint32]struct{})
	exists := struct{}{}
	for a := range counted {
		countedA := counted[a]
		_, coveredContainsA := covered[a]
		if coveredContainsA {
			continue
		}
		if a == 0 {
			total += nChooseK(countedA, 2)
			covered[a] = exists
		} else {
			b := uint32(k) - a
			if b == a {
				total += nChooseK(countedA, 2)
				covered[a] = exists
			} else {
				countedB, countedContainsB := counted[b]
				if countedContainsB {
					total += uint64(countedA * countedB)
					covered[a] = exists
					covered[b] = exists
				}
			}
		}
	}
	return int32(total)
}

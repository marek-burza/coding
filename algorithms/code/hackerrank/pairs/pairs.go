package pairs

import "slices"

// https://www.hackerrank.com/challenges/pairs

func binarySearch(array []int32, fromIndex int, toIndex int, key int32) int {
	for fromIndex <= toIndex {
		middle := (fromIndex + toIndex) / 2
		switch {
		case array[middle] < key:
			fromIndex = middle + 1
		case array[middle] > key:
			toIndex = middle - 1
		default:
			return middle
		}
	}
	return -1
}

// Pairs - implements the solution to the problem
func Pairs(k int32, arr []int32) int32 {
	slices.Sort(arr)
	count := int32(0)
	for i := 0; i < len(arr)-1; i++ {
		if binarySearch(arr, i+1, len(arr)-1, arr[i]+k) >= 0 {
			count++
		}
	}
	return count
}

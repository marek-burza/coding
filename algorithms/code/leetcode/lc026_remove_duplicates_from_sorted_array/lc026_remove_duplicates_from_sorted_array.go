package lc026

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/

// RemoveDuplicates function removes duplicates from a sorted array in place
func RemoveDuplicates(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	counter := 0
	for i := 1; i < len(nums); i++ {
		spot := counter
		if nums[i] == nums[i-1-spot] {
			counter++
		}
		nums[i-spot] = nums[i]
	}
	return len(nums) - counter
}

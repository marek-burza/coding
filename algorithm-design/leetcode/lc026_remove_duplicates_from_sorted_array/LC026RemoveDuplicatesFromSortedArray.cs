// https://leetcode.com/problems/remove-duplicates-from-sorted-array/

namespace AlgorithmDesign.leetcode.lc026_remove_duplicates_from_sorted_array
{
    public class Solution
    {
        public int RemoveDuplicates(int[] nums)
        {
            int counter = 0;
            for (int i = 1; i < nums.Length; i++)
            {
                int spot = counter;
                if (nums[i] == nums[i - 1 - spot])
                {
                    counter++;
                }
                nums[i - spot] = nums[i];
            }
            return nums.Length - counter;
        }
    }
}

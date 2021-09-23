// https://leetcode.com/problems/remove-element/

namespace AlgorithmDesign.leetcode.lc027_remove_element
{
    public class Solution
    {
        public int RemoveElement(int[] nums, int val)
        {
            if (nums == null) return 0;
            int index = 0;
            for (int i = 0; i < nums.Length; i++)
            {
                nums[index] = nums[i];
                if (nums[i] != val)
                {
                    index++;
                }
            }
            return index;
        }
    }
}

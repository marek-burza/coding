// https://leetcode.com/problems/3sum/
using System;
using System.Collections.Generic;

namespace AlgorithmDesign.leetcode.lc015_three_sum
{
    public class Solution
    {
        public IList<IList<int>> ThreeSum(int[] nums)
        {
            Array.Sort(nums);
            IList<IList<int>> result = new List<IList<int>>();
            int length = nums.Length;
            for (int i = 0; i < length; i++)
            {
                int j = i + 1;
                int k = length - 1;
                if (i > 0 && nums[i] == nums[i - 1])
                {
                    continue;
                }
                while (j < k)
                {
                    if (k < length - 1 && nums[k] == nums[k + 1])
                    {
                        k--;
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] > 0)
                    {
                        k--;
                    }
                    else if (nums[i] + nums[j] + nums[k] < 0)
                    {
                        j++;
                    }
                    else
                    {
                        IList<int> triplet = new List<int>();
                        triplet.Add(nums[i]);
                        triplet.Add(nums[j]);
                        triplet.Add(nums[k]);
                        result.Add(triplet);
                        j++;
                        k--;
                    }
                }
            }
            return result;
        }
    }
}

// https://leetcode.com/problems/container-with-most-water/
using System;

namespace AlgorithmDesign.leetcode.lc011_container_with_most_water
{
    public class Solution
    {
        public int MaxArea(int[] height)
        {
            int max = 0;
            int low = 0;
            int high = height.Length - 1;
            while (low < high)
            {
                int top = Math.Min(height[low], height[high]);
                max = Math.Max(max, top * (high - low));
                if (height[low] <= height[high])
                {
                    low++;
                }
                else
                {
                    high--;
                }
            }
            return max;
        }
    }
}

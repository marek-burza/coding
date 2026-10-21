# https://leetcode.com/problems/maximum-product-subarray/
# #medium


class Solution:
    def maxProduct(self, nums: list[int]) -> int:
        maximum = min(nums) - 1  # Instead of -inf
        cmin = 0
        cmax = 0
        for num in nums:
            tmin = num
            tmax = num
            if cmin != 0:
                tmin *= cmin
            if cmax != 0:
                tmax *= cmax
            cmin = min(tmax, tmin)
            cmin = min(cmin, num)
            cmax = max(tmin, tmax)
            cmax = max(num, cmax)
            maximum = max(maximum, cmax)
        return maximum


class TestCode:
    def test_2_3_Minus2_4(self) -> None:
        nums = [2, 3, -2, 4]
        assert Solution().maxProduct(nums) == 6

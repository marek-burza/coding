# https://leetcode.com/problems/best-time-to-buy-and-sell-stock/


class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        if len(prices) <= 1:
            return 0
        mins = [0 for _ in range(len(prices))]
        mins[0] = prices[0]
        i = 1
        while i < len(prices):
            mins[i] = min(mins[i - 1], prices[i])
            i += 1
        profit = min(prices) - max(mins)  # Instead of -inf
        maximum = prices[len(prices) - 1]
        for i in range(len(prices) - 1, -1, -1):
            maximum = max(maximum, prices[i])
            delta = maximum - mins[i]
            profit = max(profit, delta)
        return profit


class TestCode:
    def test_empty(self) -> None:
        assert Solution().maxProfit([]) == 0

    def test_1(self) -> None:
        assert Solution().maxProfit([1]) == 0

    def test_example_1(self) -> None:
        assert Solution().maxProfit([7, 1, 5, 3, 6, 4]) == 5

    def test_example_2(self) -> None:
        assert Solution().maxProfit([7, 6, 4, 3, 1]) == 0

# https://leetcode.com/problems/coin-change
# #medium


class Solution:
    def coinChange(self, coins: list[int], amount: int) -> int:
        table = [amount + 1] * (amount + 1)
        table[0] = 0
        for index in range(1, amount + 1):
            for coin in coins:
                if index - coin >= 0:
                    table[index] = min(table[index], 1 + table[index - coin])
        return table[amount] if table[amount] != amount + 1 else -1


class TestCode:
    def test_example_1(self) -> None:
        assert Solution().coinChange([1, 2, 5], 11) == 3

    def test_example_2(self) -> None:
        assert Solution().coinChange([2], 3) == -1

    def test_example_3(self) -> None:
        assert Solution().coinChange([1], 0) == 0

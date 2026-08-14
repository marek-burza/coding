# https://www.hackerrank.com/challenges/diagonal-difference


def diagonal_difference(arr: list[list[int]]) -> int:
    result = 0
    n = len(arr)
    for i in range(n):
        result += arr[i][i] - arr[n - 1 - i][i]
    return abs(result)


class TestCode:
    def test_example(self) -> None:
        arr = [[11, 2, 4], [4, 5, 6], [10, 8, -12]]
        assert diagonal_difference(arr) == 15

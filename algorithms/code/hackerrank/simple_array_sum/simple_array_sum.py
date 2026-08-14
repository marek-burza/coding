# https://www.hackerrank.com/challenges/simple-array-sum


def simple_array_sum(ar: list[int]) -> int:
    return sum(ar)


class TestCode:
    def test_example(self) -> None:
        assert simple_array_sum([1, 2, 3, 4, 10, 11]) == 31

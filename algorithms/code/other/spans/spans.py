def compress_array(integers: list[int]) -> str:
    integers.sort()
    ranges, start = [], None
    for index, current in enumerate(integers):
        if start is None:
            start = current
        if index == len(integers) - 1 or integers[index + 1] - current > 1:
            ranges.append(str(current) if start == current else f"{start}-{current}")
            start = None
    return ",".join(ranges)


class TestCode:
    def test_123478(self) -> None:
        assert compress_array([4, 2, 7, 1, 3, 8]) == "1-4,7-8"

    def test_1(self) -> None:
        assert compress_array([1]) == "1"

    def test_12(self) -> None:
        assert compress_array([2, 1]) == "1-2"

    def test_14(self) -> None:
        assert compress_array([1, 4]) == "1,4"

    def test_nothing(self) -> None:
        assert compress_array([]) == ""

    def test_12345(self) -> None:
        assert compress_array([4, 2, 5, 1, 3]) == "1-5"

    def test_124578(self) -> None:
        assert compress_array([4, 2, 5, 1, 8, 7]) == "1-2,4-5,7-8"

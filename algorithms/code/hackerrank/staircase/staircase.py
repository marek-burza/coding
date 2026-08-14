# https://www.hackerrank.com/challenges/staircase


def staircase(n: int) -> list[str]:
    result = []
    for index in range(n):
        line = ""
        for i in range(n):
            line += " " if i < n - 1 - index else "#"
        result.append(line)
    return result


class TestCode:
    def test_example(self) -> None:
        expected = ["     #", "    ##", "   ###", "  ####", " #####", "######"]
        assert expected == staircase(6)

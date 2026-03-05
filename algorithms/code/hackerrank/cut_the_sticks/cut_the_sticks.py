# https://www.hackerrank.com/challenges/cut-the-sticks

import os
import unittest


def cut_the_sticks(arr: list[int]) -> list[int]:
    arr = sorted(arr)
    cuts = []
    for index, i in enumerate(range(len(arr) - 1, -1, -1)):
        count = index + 1
        if i == 0 or arr[i] != arr[i - 1]:
            cuts.append(count)
    return cuts[::-1]


class TestCode(unittest.TestCase):
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        arguments = [int(item) for item in io_lines[0][1]]
        result = cut_the_sticks(arguments)
        expected = [int(line[0]) for line in io_lines[1]]
        assert expected == result

    def test_example_0(self) -> None:
        self.runner('_example_0')

    def test_example_1(self) -> None:
        self.runner('_example_1')

# https://www.hackerrank.com/challenges/bigger-is-greater

import unittest


def bigger_is_greater(w: str) -> str:
    array = list(w)
    length = len(array)
    for i in range(length - 1, 0, -1):
        if array[i - 1] < array[i]:
            array[i:] = sorted(array[i:])
            for j in range(i, length):
                if array[i - 1] < array[j]:
                    array[i - 1], array[j] = array[j], array[i - 1]
                    array[i:] = sorted(array[i:])
                    return ''.join(array)
    return 'no answer'


class TestCode(unittest.TestCase):
    def test_ab(self) -> None:
        assert bigger_is_greater('ab') == 'ba'

    def test_bb(self) -> None:
        assert bigger_is_greater('bb') == 'no answer'

    def test_hefg(self) -> None:
        assert bigger_is_greater('hefg') == 'hegf'

    def test_dhck(self) -> None:
        assert bigger_is_greater('dhck') == 'dhkc'

    def test_dkhc(self) -> None:
        assert bigger_is_greater('dkhc') == 'hcdk'

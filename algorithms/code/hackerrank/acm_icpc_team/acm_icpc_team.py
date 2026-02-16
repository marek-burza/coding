#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/acm-icpc-team

import unittest


def acm_team(topic):
    length = len(topic)
    coverage_count = 0
    teams_count = 0
    for i in range(length - 1):
        for j in range(i + 1, length):
            count = 0
            for pair in zip(topic[i], topic[j], strict=True):
                count += 1 if '1' in pair else 0
            if count > coverage_count:
                coverage_count = count
                teams_count = 1
            elif count == coverage_count:
                teams_count += 1
    return (coverage_count, teams_count)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        topic = ['10101', '11100', '11010', '00101']
        expected = (5, 2)
        result = acm_team(topic)
        self.assertEqual(result, expected)

    def test_example_2(self):
        topic = ['10101', '11110', '00010']
        expected = (5, 1)
        result = acm_team(topic)
        self.assertEqual(result, expected)

#!/usr/bin/env python3
# https://web.archive.org/web/20170803130133/http://qa.geeksforgeeks.org/6310/find-the-nearest-locker-in-the-city

import queue
import sys
import unittest

DIRECTIONS = [(0, 1), (0, -1), (1, 0), (-1, 0)]


def init_city(sizex, sizey):
    return [[0] * sizey for _ in range(sizex)]


def fill_city(sizex, sizey, city, value):
    for x in range(sizex):
        for y in range(sizey):
            city[x][y] = value


def mark_lockers(city, lockers):
    if not lockers:
        return
    for locker in lockers:
        city[locker[0]][locker[1]] = 0


def init_bfs(lockers, queued):
    if not lockers:
        return
    for locker in lockers:
        queued.put(locker)


def progress_bfs(sizex, sizey, city, queued):
    position = queued.get()
    distance = city[position[0]][position[1]] + 1
    for direction in DIRECTIONS:
        x = position[0] + direction[0]
        y = position[1] + direction[1]
        if 0 <= x < sizex and 0 <= y < sizey and distance < city[x][y]:
            city[x][y] = distance
            queued.put((x, y))


def locker_distances(sizex, sizey, lockers):
    if sizex < 0 or sizey < 0:
        return None
    city = init_city(sizex, sizey)
    fill_city(sizex, sizey, city, sys.maxsize)
    mark_lockers(city, lockers)
    queued = queue.Queue()
    init_bfs(lockers, queued)
    while not queued.empty():
        progress_bfs(sizex, sizey, city, queued)
    return city


class TestCode(unittest.TestCase):
    def test_bad_size(self):
        result = locker_distances(-1, -1, None)
        self.assertEqual(result, None)

    def test_result_present(self):
        result = locker_distances(1, 1, None)
        self.assertEqual(result, [[sys.maxsize]])

    def test_result_just_locker(self):
        lockers = [(0, 0)]
        result = locker_distances(1, 1, lockers)
        self.assertEqual(result, [[0]])

    def test_example1(self):
        lockers = [(0, 0)]
        result = locker_distances(3, 5, lockers)
        expected = [[0, 1, 2, 3, 4], [1, 2, 3, 4, 5], [2, 3, 4, 5, 6]]
        self.assertEqual(result, expected)

    def test_example2(self):
        lockers = [(1, 2), (3, 6)]
        result = locker_distances(5, 7, lockers)
        expected = [
            [3, 2, 1, 2, 3, 4, 3],
            [2, 1, 0, 1, 2, 3, 2],
            [3, 2, 1, 2, 3, 2, 1],
            [4, 3, 2, 3, 2, 1, 0],
            [5, 4, 3, 4, 3, 2, 1],
        ]
        self.assertEqual(result, expected)

#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/piling-up

import collections


def check(cubes):
    q = collections.deque(cubes)
    previous = None
    while q:
        left = q[0]
        right = q[-1]
        if left < right:
            current = q.pop()
        else:
            current = q.popleft()
        if previous is not None and previous < current:
            return False
        previous = current
    return True


def main():
    t = int(input().strip())
    for _ in range(t):
        n = int(input().strip())
        cubes = list(map(int, input().strip().split()))[:n]
        print('Yes' if check(cubes) else 'No')

if __name__ == '__main__':
    main()

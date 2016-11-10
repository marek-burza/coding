#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/incorrect-regex

import re
import sre_constants


def check(pattern):
    try:
        re.compile(pattern)
        return True
    except sre_constants.error as exception:
        return False


def main():
    n = int(input().strip())
    for i in range(n):
        print(check(input().strip()))

if __name__ == '__main__':
    main()

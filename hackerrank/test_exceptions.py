#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/exceptions


def exempt(a, b):
    try:
        return int(a) // int(b)
    except Exception as exception:
        return 'Error Code: %s' % str(exception)


def main():
    n = int(input().strip())
    for i in range(n):
        a, b = input().strip().split()
        print(exempt(a, b))

if __name__ == '__main__':
    main()

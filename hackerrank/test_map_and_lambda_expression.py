#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/map-and-lambda-expression


def fibonacci(n):
    listed = [0, 1]
    while len(listed) < n:
        listed.append(listed[-1] + listed[-2])
    return listed[:n]


def square(n):
    listed = fibonacci(n)
    listed = list(map(lambda x: x * x * x, listed))
    return listed


def main():
    n = int(input().strip())
    print(square(n))

if __name__ == '__main__':
    main()

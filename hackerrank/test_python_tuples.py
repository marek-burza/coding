#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-tuples

def convert(integers):
    return hash(tuple(integers))


def main():
    n = int(input().strip())
    integers = list(map(int, input().strip().split(' ')))[:n]
    print(convert(integers))

if __name__ == '__main__':
    main()

#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/no-idea


def calculate_happiness(array, a_in, b_in):
    happiness = 0
    for item in array:
        if item in a_in:
            happiness += 1
        elif item in b_in:
            happiness -= 1
    return happiness


def main():
    input() #n_in, m_in = list(map(int, input().strip().split(' ')))
    array = input().strip().split(' ')
    a_in = set(input().strip().split(' '))
    b_in = set(input().strip().split(' '))
    print(calculate_happiness(array, a_in, b_in))

if __name__ == '__main__':
    main()

#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/standardize-mobile-number-using-decorators


def decorate_numbers(numbers):
    def applier():
        return ['+91 %s %s' % (number[-10:-5], number[-5:]) for number in numbers]
    return applier


def standardize(numbers):
    numbers = [number[-10:] for number in numbers]
    numbers = sorted(numbers)
    decorated = decorate_numbers(numbers)
    return decorated()


def main():
    n = int(input().strip())
    numbers = []
    for i in range(n):
        numbers.append(input().strip())
    for number in standardize(numbers):
        print(number)

if __name__ == '__main__':
    main()

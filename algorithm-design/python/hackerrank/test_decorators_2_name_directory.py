#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/decorators-2-name-directory

import operator


def title(gender):
    return 'Mr.' if gender == 'M' else 'Ms.'

def decorate_gender(entries):
    def applier():
        return ['%s %s %s' % (title(entry[3]), entry[0], entry[1]) for entry in entries]
    return applier


def standardize(entries):
    entries.sort(key=operator.itemgetter(2))
    decorated = decorate_gender(entries)
    return decorated()


def main():
    n = int(input().strip())
    entries = []
    for _ in range(n):
        entries.append(input().strip().split())
    for entry in standardize(entries):
        print(entry)

if __name__ == '__main__':
    main()

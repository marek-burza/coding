#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/word-order

import collections


def orderly(words):
    unique = []
    counted = collections.Counter()
    for word in words:
        if word not in counted:
            unique.append(word)
        counted[word] += 1
    return unique, counted


def main():
    n = int(input().strip())
    words = []
    for _ in range(n):
        words.append(input().strip())
    unique, counted = orderly(words)
    print(len(unique))
    print(' '.join([str(counted[word]) for word in unique]))

if __name__ == '__main__':
    main()

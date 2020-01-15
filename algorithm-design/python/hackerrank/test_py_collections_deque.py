#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-deque

import collections


def operate(operations):
    q = collections.deque()
    for operation in operations:
        if operation[0] == 'append':
            q.append(operation[1])
        elif operation[0] == 'pop':
            q.pop()
        elif operation[0] == 'popleft':
            q.popleft()
        elif operation[0] == 'appendleft':
            q.appendleft(operation[1])
    return q


def main():
    n = int(input().strip())
    operations = []
    for _ in range(n):
        operation = input().strip().split()
        operations.append(operation)
    print(' '.join(operate(operations)))

if __name__ == '__main__':
    main()

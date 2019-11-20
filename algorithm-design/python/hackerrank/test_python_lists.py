#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-lists


def process(operations):
    listed = []
    for operation in operations:
        if operation[0] == 'insert':
            listed.insert(operation[1], operation[2])
        elif operation[0] == 'print':
            print(listed)
        elif operation[0] == 'remove':
            listed.remove(operation[1])
        elif operation[0] == 'append':
            listed.append(operation[1])
        elif operation[0] == 'sort':
            listed.sort()
        elif operation[0] == 'pop':
            listed.pop()
        elif operation[0] == 'reverse':
            listed.reverse()


def main():
    n = int(input().strip())
    operations = []
    for i in range(n):
        operation = input().strip().split(' ')
        if operation[0] in ['insert', 'remove', 'append']:
            operation[1] = int(operation[1])
            if operation[0] == 'insert':
                operation[2] = int(operation[2])
        operations.append(operation)
    process(operations)

if __name__ == '__main__':
    main()

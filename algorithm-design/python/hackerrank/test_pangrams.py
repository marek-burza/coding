#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/pangrams


def pangram(text):
    text = text.lower()
    remaining = {chr(i) for i in range(ord('a'), ord('z') + 1)}
    for key in text:
        if key in remaining:
            remaining.remove(key)
        if len(remaining) == 0:
            return True
    return False


def main():
    print('pangram' if pangram(input().strip()) else 'not pangram')

if __name__ == '__main__':
    main()

#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/validate-list-of-email-address-with-filter

import re


def check(email):
    items = email.split('@')
    if len(items) != 2:
        return False
    if not re.match('[\w-]+$', items[0]):
        return False
    items = items[1].split('.')
    if len(items) != 2:
        return False
    if not items[0].isalnum():
        return False
    return 0 < len(items[1]) <= 3

def process(emails):
    emails = list(filter(lambda email: check(email), emails))
    emails.sort()
    return emails


def main():
    n = int(input().strip())
    emails = []
    for i in range(n):
        emails.append(input().strip())
    print(process(emails))

if __name__ == '__main__':
    main()

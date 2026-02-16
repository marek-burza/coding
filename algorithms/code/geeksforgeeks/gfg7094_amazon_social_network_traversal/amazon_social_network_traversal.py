#!/usr/bin/env python3
# https://web.archive.org/web/20180317031648/http://qa.geeksforgeeks.org/7094/amazon-social-network-traversal

import collections
import functools
import operator
import unittest


class GFG7094AmazonSocialNetworkTraversal:
    def __init__(self, friendships, attendances):
        self.friendships = friendships
        self.attendances = attendances

    def get_direct_friends_for_user(self, user):
        return self.friendships.get(user, set())

    def get_attended_courses_for_user(self, user):
        return self.attendances.get(user, set())

    def get_ranked_courses(self, user):
        own_friends = self.get_direct_friends_for_user(user)
        second_degree = map(self.get_direct_friends_for_user, own_friends)
        network = set().union(*second_degree) - {user}
        own_courses = self.get_attended_courses_for_user(user)
        second_courses = map(
            lambda friend: list(
                self.get_attended_courses_for_user(friend) - own_courses
            ),
            network,
        )
        second_courses = functools.reduce(operator.iconcat, second_courses, [])
        counted_courses = collections.Counter(second_courses)
        ranked_courses = sorted(
            counted_courses.keys(),
            key=lambda course: counted_courses[course],
            reverse=True,
        )
        return ranked_courses


class TestCode(unittest.TestCase):
    EXAMPLE_FRIENDSHIPS = {
        'Jack': {'Jane', 'John'},
        'John': {'Alice', 'Jack', 'Jane'},
        'Alice': {'Bob'},
        'Bob': {'Alice', 'Jane'},
        'Jane': {'Jack', 'John'},
        'Loner': set(),
    }
    EXAMPLE_ATTENDANCES = {
        'Jack': {'Science 1'},
        'John': {'Science 1', 'Arts'},
        'Alice': {'Science 1', 'Science 2', 'Arts'},
        'Bob': {'Arts', 'Sports'},
        'Jane': {'Science 1', 'Science 2', 'Arts', 'Sports'},
        'Loner': {'Philosophy'},
    }

    def test_loner(self):
        network = GFG7094AmazonSocialNetworkTraversal(
            TestCode.EXAMPLE_FRIENDSHIPS, TestCode.EXAMPLE_ATTENDANCES
        )
        result = network.get_ranked_courses('Loner')
        self.assertEqual(result, [])

    def test_jack(self):
        network = GFG7094AmazonSocialNetworkTraversal(
            TestCode.EXAMPLE_FRIENDSHIPS, TestCode.EXAMPLE_ATTENDANCES
        )
        result = network.get_ranked_courses('Jack')
        self.assertEqual(result, ['Arts', 'Science 2', 'Sports'])

    def test_jane(self):
        network = GFG7094AmazonSocialNetworkTraversal(
            TestCode.EXAMPLE_FRIENDSHIPS, TestCode.EXAMPLE_ATTENDANCES
        )
        result = network.get_ranked_courses('Jane')
        self.assertEqual(result, [])

    def test_leftovers(self):
        network = GFG7094AmazonSocialNetworkTraversal(
            TestCode.EXAMPLE_FRIENDSHIPS, TestCode.EXAMPLE_ATTENDANCES
        )
        result = network.get_attended_courses_for_user('')
        self.assertEqual(len(result), 0)
        result = network.get_direct_friends_for_user('')
        self.assertEqual(len(result), 0)
        friendships = {
            'Student1': {'Student2', 'Student3'},
            'Student2': {'Student1', 'Student3'},
            'Student3': {'Student1', 'Student2'},
        }
        attendances = {
            'Student1': {'Course1'},
            'Student2': {'Course2'},
            'Student3': {'Course3'},
        }
        network = network = GFG7094AmazonSocialNetworkTraversal(
            friendships, attendances
        )
        self.assertEqual(2, len(network.get_ranked_courses('Student1')))

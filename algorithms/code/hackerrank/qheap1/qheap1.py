#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/qheap1

import heapq
import io
import os
import sys
import unittest


def sheap_child_left(index):
    return (index << 1) + 1


def sheap_child_right(index):
    return (index << 1) + 2


def sheap_parent(index):
    return (index - 1) >> 1


def sheap_swap(heap, index1, index2):
    heap[index1], heap[index2] = heap[index2], heap[index1]


def sheap_ify_to_leaves(heap, index):
    size = len(heap)
    # Initialize smallest as root
    smallest = index
    # Left
    left = sheap_child_left(index)
    # Right
    right = sheap_child_right(index)
    # Check if left child is smallest
    if left < size and heap[left] < heap[smallest]:
        smallest = left
    # Check if right child is smallest
    if right < size and heap[right] < heap[smallest]:
        smallest = right
    # If smallest is not root
    if smallest != index:
        # Swap
        sheap_swap(heap, smallest, index)
        # Recursively heapify the affected sub-tree
        sheap_ify_to_leaves(heap, smallest)


def sheap_ify_to_root(heap, index):
    while index > 0 and heap[sheap_parent(index)] > heap[index]:
        sheap_swap(heap, index, sheap_parent(index))
        index = sheap_parent(index)


def sheap_build(heap):
    size = len(heap)
    index = (size // 2) - 1
    while index >= 0:
        sheap_ify_to_leaves(heap, index)
        index -= 1


def sheap_insert(heap, value):
    heap.append(value)
    index = len(heap) - 1
    sheap_ify_to_root(heap, index)


def sheap_delete_index(heap, index):
    size = len(heap)
    sheap_swap(heap, index, size - 1)
    heap.pop()  # Can't swap on a popped value so first swap
    if index == size - 1:
        return
    if index == 0 or heap[sheap_parent(index)] < heap[index]:
        sheap_ify_to_leaves(heap, index)
    else:
        sheap_ify_to_root(heap, index)


def sheap_delete(heap, value):
    sheap_delete_index(heap, sheap_search(heap, value))


def sheap_search(heap, value):
    try:
        return heap.index(value)
    except ValueError:
        return None
    # Nodes at any level are unsorted
    # so stopping on first larger item might be premature


def sheap_root(heap):
    return heap[0]


def qheap_insert(heap, value):
    heapq.heappush(heap, value)


def qheap_delete(heap, value):
    index = heap.index(value)
    heap[index] = heap[-1]
    heap.pop()
    if index < len(heap):
        heapq._siftup(  # pylint: disable=protected-access,C0301  # ty: ignore[unresolved-attribute]  # pyright: ignore[reportAttributeAccessIssue]  # noqa: E501
            heap, index
        )
        heapq._siftdown(  # pylint: disable=protected-access,C0301  # ty: ignore[unresolved-attribute]  # pyright: ignore[reportAttributeAccessIssue]  # noqa: E501
            heap, 0, index
        )


def qheap_root(heap):
    return heap[0]


def main(quick=True):
    if quick:
        heap_insert = qheap_insert
        heap_delete = qheap_delete
        heap_root = qheap_root
    else:
        heap_insert = sheap_insert
        heap_delete = sheap_delete
        heap_root = sheap_root
    n = int(input().strip())
    heap = []
    for _ in range(n):
        arguments = list(map(int, input().split()))
        if arguments[0] == 1:
            heap_insert(heap, arguments[1])
        elif arguments[0] == 2:
            heap_delete(heap, arguments[1])
        elif arguments[0] == 3:
            print(heap_root(heap))


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which, quick=True):
        resources = os.path.dirname(__file__)
        with (
            open(
                os.path.join(resources, f'output{which}.txt'),
                'r',
                encoding='utf-8',
            ) as expected,
            open(
                os.path.join(resources, f'input{which}.txt'),
                'r',
                encoding='utf-8',
            ) as sys.stdin,
            io.StringIO() as sys.stdout,
        ):
            main(quick)
            self.assertEqual(sys.stdout.getvalue(), expected.read())

    def test_search_for_absent(self):
        heap = [6, 3, 0, 5]
        sheap_build(heap)
        self.assertIsNone(sheap_search(heap, -1))

    def test_delete_swaps_on_same_level(self):
        heap = [0, 10, 8, 13, 14, 9]
        sheap_delete_index(heap, 4)
        self.assertNotEqual(heap[-1], 9)

    def test_example(self):
        self.generalized_test('example')
        self.generalized_test('example', False)

    def test_00(self):
        self.generalized_test('00')
        self.generalized_test('00', False)

    def test_01(self):
        self.generalized_test('01')
        self.generalized_test('01', False)

    def test_02(self):
        self.generalized_test('02')
        self.generalized_test('02', False)

    def test_08(self):
        self.generalized_test('08')

// https://www.hackerrank.com/challenges/qheap1
package hackerrank.qheap1;

import java.util.List;

public final class SolutionCore {
    private SolutionCore() {
    }

    private static int heapChildLeft(final int index) {
        return (index << 1) + 1;
    }

    private static int heapChildRight(final int index) {
        return (index << 1) + 2;
    }

    private static int heapParent(final int index) {
        return (index - 1) >> 1;
    }

    private static void heapSwap(final List<Integer> heap, final int index1, final int index2) {
        int exchange = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, exchange);
    }

    private static void heapIfyToLeaves(final List<Integer> heap, final int index) {
        int size = heap.size();
        // Initialize smallest as root
        int smallest = index;
        // Left
        int left = heapChildLeft(index);
        // Right
        int right = heapChildRight(index);
        // Check if left child is smallest
        if (left < size && heap.get(left) < heap.get(smallest)) {
            smallest = left;
        }
        // Check if right child is smallest
        if (right < size && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }
        // If smallest is not root
        if (smallest != index) {
            // Swap
            heapSwap(heap, smallest, index);
            // Recursively heapify the affected sub-tree
            heapIfyToLeaves(heap, smallest);
        }
    }

    private static void heapIfyToRoot(final List<Integer> heap, final int index) {
        int current = index;
        while (current > 0 && heap.get(heapParent(current)) > heap.get(current)) {
            heapSwap(heap, current, heapParent(current));
            current = heapParent(current);
        }
    }

    public static void heapBuild(final List<Integer> heap) {
        int size = heap.size();
        int index = (size / 2) - 1;
        while (index >= 0) {
            heapIfyToLeaves(heap, index);
            index--;
        }
    }

    public static void heapInsert(final List<Integer> heap, final int value) {
        heap.add(value);
        int index = heap.size() - 1;
        heapIfyToRoot(heap, index);
    }

    public static void heapDeleteIndex(final List<Integer> heap, final int index) {
        int size = heap.size();
        heapSwap(heap, index, size - 1);
        heap.remove(size - 1); // Can't swap on a popped value so first swap
        if (index == size - 1) {
            return;
        }
        if (index == 0 || heap.get(heapParent(index)) < heap.get(index)) {
            heapIfyToLeaves(heap, index);
        } else {
            heapIfyToRoot(heap, index);
        }
    }

    public static void heapDelete(final List<Integer> heap, final int value) {
        heapDeleteIndex(heap, heapSearch(heap, value));
    }

    public static int heapSearch(final List<Integer> heap, final int value) {
        return heap.indexOf(value);
        // Nodes at any level are unsorted
        // so stopping on first larger item might be premature
    }

    public static Integer heapRoot(final List<Integer> heap) {
        return heap.get(0);
    }
}

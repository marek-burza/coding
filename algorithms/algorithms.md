# Algorithms

---

## DYNAMIC PROGRAMMING

TOP-DOWN: MEMOIZATION<br/>
BOTTOM-UP: TABULATION (SUBPROBLEMS)

---

## HEAPS (functioning)

[qheap1](https://www.hackerrank.com/challenges/qheap1)
[min-stack](https://leetcode.com/problems/min-stack/)
[kth-largest-element-in-an-array](https://leetcode.com/problems/kth-largest-element-in-an-array/)

_CHILD = INDEX × 2_<br/>
_PARENT = INDEX / 2_<br/>
(correct index by 1 for 0-based array; _O_ - WORST/AVG)<br/>

- BUILD: for each index from last parent to 0 (the root) traverse down picking the biggest/smallest child and correcting parent-child order
- INSERT: append to the end of the array and traverse up correcting parent-child order _O(log(n))_
- DELETE: overwrite node with last one and if less/greater than parent traverse up/down _O(log(n))_
- SEARCH: linear scan through the array _O(n)_

---

## GRAPHS

- **ADJACENCY MATRIX**<br/>
  PRO: LOOKUP TIME (MANY CONNECTIONS)<br/>
  CON: SIZE (ALL POSSIBLE CONNECTIONS)
- **ADJACENCY LIST**<br/>
  PRO: SIZE, SPEED (FEW CONNECTIONS), SPARSE
  **SPACE TRADE-OFF:** _X × E vs. N² / 8_<br/>
  (X - pointer size in bytes; matrix is packed - 8 booleans per byte)
- **OBJECTS AND POINTERS**

---

## QUICK SORT

- (**shuffle** first or sample for pivot - median as pivot helps; [LeetCode: shuffle-an-array](https://leetcode.com/problems/shuffle-an-array/))
- PICK PIVOT
- REORDER WITH RESPECT TO PIVOT
- APPLY TO ARRAYS SEPARATED BY PIVOT

UNSTABLE<br/>
WORST: _O(N²)_ **!!!**<br/>
BEST: _O(N × log(N))_<br/>
AVERAGE: _O(N × log(N))_

---

## MERGE SORT

- DIVIDE INTO SUBLISTS
- SORT SUBLISTS RECURSIVELY
- MERGE SUBLISTS

STABLE<br/>
WORST: _O(N × log(N))_ **!!!**<br/>
BEST: _O(N × log(N))_<br/>
AVERAGE: _O(N × log(N))_
